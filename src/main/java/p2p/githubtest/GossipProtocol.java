package p2p.githubtest;

import com.google.common.base.Charsets;
import io.libp2p.core.Stream;
import io.libp2p.protocol.ProtocolHandler;
import io.libp2p.protocol.ProtocolMessageHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import p2p.githubtest.message.Message;
import p2p.githubtest.message.Type;

import javax.inject.Inject;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GossipProtocol extends ProtocolHandler<GossipController> {
    private static final Logger LOGGER = Logger.getLogger("GossipProtocol");

    @Inject
    HeartbeatMsg heartbeat;

    private final CompletableFuture<GossipController> completableFuture = new CompletableFuture<>();
    private final GossipHandler gossipHandler = new GossipHandler(completableFuture);

    public GossipProtocol(long initiatorTrafficLimit, long responderTrafficLimit) {
        super(initiatorTrafficLimit, responderTrafficLimit);
    }

    @NotNull
    @Override
    public CompletableFuture<GossipController> onStartInitiator(@NotNull Stream stream) {
        LOGGER.info("onStartInitiator remote peerId :" + stream.remotePeerId());
        stream.pushHandler(gossipHandler);
        return completableFuture;
    }

    @NotNull
    @Override
    public CompletableFuture<GossipController> onStartResponder(@NotNull Stream stream) {
        LOGGER.info("onStartResponder remote peerId :" + stream.remotePeerId());
        stream.pushHandler(gossipHandler);
        return completableFuture;
    }

    public static class GossipHandler implements ProtocolMessageHandler<ByteBuf>, GossipController {
        CompletableFuture<GossipController> completableFuture;
        private Stream stream;
        private ScheduledFuture<?> scheduledFuture;
        private ScheduledExecutorService heartbeatScheduler;

        public GossipHandler(CompletableFuture<GossipController> completableFuture) {
            this.completableFuture = completableFuture;
        }

        @Override
        public void onMessage(@NotNull Stream stream, ByteBuf msg) {
            String msgStr = msg.toString(Charsets.UTF_8);
            LOGGER.info("GossipHandler onMessage, remote peerId :" + stream.remotePeerId() + " , msg :" + msgStr);
            //LOGGER.info("GossipHandler onMessage, remote peerId :" + stream.remotePeerId());

        }

        @Override
        public void onException(@Nullable Throwable throwable) {
            LOGGER.log(Level.SEVERE, "Exception received", throwable);
            LOGGER.info("GossipHandler onException");
        }

        @Override
        public void onClosed(@NotNull Stream stream) {
            LOGGER.info("GossipHandler onClosed");
            heartbeatScheduler.shutdown();
            scheduledFuture.cancel(true);
        }

        @Override
        public void onActivated(@NotNull Stream stream) {
            LOGGER.info("GossipHandler onActivated");
            this.stream = stream;

            heartbeatScheduler = Executors.newScheduledThreadPool(1);
            Runnable heartbeat = this::heartbeat;
            scheduledFuture = heartbeatScheduler.scheduleAtFixedRate(heartbeat, 0, 30, TimeUnit.SECONDS);

            completableFuture.complete(this);
        }

        @Override
        public void fireMessage(@NotNull Stream stream, @NotNull Object o) {
            // LOGGER.info("GossipHandler fireMessage " + o.getClass().getName());
            onMessage(stream, (ByteBuf) o);
        }

        @Override
        public CompletableFuture<String> chat(String msg) {
            LOGGER.info("send msg :" + msg);
            CompletableFuture<String> ret = new CompletableFuture<>();
            ByteBuf data = Unpooled.wrappedBuffer(msg.getBytes());
            stream.writeAndFlush(data);
            ret.complete("chat ok");
            return ret;
        }

        @Override
        public void heartbeat() {

            Message message = new Message();
            message.setId(new Type((byte) 4));
            message.setMaxBytesLength(Message.heartbeatMilestoneIndexBytesLength * 3 + 2);
            message.setVariableLength(false);

            int solidMilestoneIndex = 1067599;
            int prunedMilestoneIndex = 1067599;
            int latestMilestoneIndex = 1067599;
            byte connectedPeers = 0;
            byte syncedPeers = 0;
            int headerMessageDefinitionMaxBytesLength = 3;


            byte[] header = new byte[headerMessageDefinitionMaxBytesLength];

            // create header
            header[0] = message.getId().getType();
            header[1] = (byte) message.getMaxBytesLength();

            ByteBuffer byteBuffer = ByteBuffer.allocate(headerMessageDefinitionMaxBytesLength + message.getMaxBytesLength()).order(ByteOrder.LITTLE_ENDIAN);
            byteBuffer.put(header, 0, headerMessageDefinitionMaxBytesLength);
            byteBuffer.putInt(solidMilestoneIndex);
            byteBuffer.putInt(prunedMilestoneIndex);
            byteBuffer.putInt(latestMilestoneIndex);
            byteBuffer.put(connectedPeers);
            byteBuffer.put(syncedPeers);

            ByteBuf data = Unpooled.wrappedBuffer(byteBuffer.array());
            stream.writeAndFlush(data);

            LOGGER.info("heartbeat sent to " + stream.remotePeerId());

        }
    }
}

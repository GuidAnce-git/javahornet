package p2p.githubtest;

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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.concurrent.TimeUnit.SECONDS;

public class GossipProtocol extends ProtocolHandler<GossipController> {
    private static final Logger LOGGER = Logger.getLogger("GossipProtocol");

    @Inject
    HeartbeatMsg heartbeat;

    static ScheduledExecutorService heartbeatScheduler = Executors.newScheduledThreadPool(1);


    private final CompletableFuture<GossipController> completableFuture = new CompletableFuture<>();
    private final GossipHandler gossipHandler = new GossipHandler(completableFuture);
    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

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

        public GossipHandler(CompletableFuture<GossipController> completableFuture) {
            this.completableFuture = completableFuture;
        }

        @Override
        public void onMessage(@NotNull Stream stream, ByteBuf msg) {
            String msgStr = msg.toString();
            // LOGGER.info("GossipHandler onMessage, remote peerId :" + stream.remotePeerId() + " , msg :" + msgStr);
            // LOGGER.info("GossipHandler onMessage, remote peerId :" + stream.remotePeerId());

        }

        @Override
        public void onException(@Nullable Throwable throwable) {
            LOGGER.log(Level.SEVERE, "Exception received", throwable);
            LOGGER.info("GossipHandler onException");
        }

        @Override
        public void onClosed(@NotNull Stream stream) {
            heartbeatScheduler.shutdown();
            LOGGER.info("GossipHandler onClosed");
        }

        @Override
        public void onActivated(@NotNull Stream stream) {
            LOGGER.info("GossipHandler onActivated");


            this.stream = stream;

            Runnable heartbeatThread = this::heartbeat;
            heartbeatScheduler.scheduleAtFixedRate(heartbeatThread, 0, 5, SECONDS);


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

            int solidMilestoneIndex = 1066249;
            int prunedMilestoneIndex = 1066249;
            int latestMilestoneIndex = 1066249;
            int connectedPeers = 0;
            int syncedPeers = 0;
            int headerMessageDefinitionMaxBytesLength = 3;
            int heartbeatMessageDefinitionMaxBytesLength = 14;

            byte[] header = new byte[headerMessageDefinitionMaxBytesLength];
            byte[] b = new byte[message.getMaxBytesLength()];

            // create header
            header[0] = message.getId().getType();
            header[1] = (byte) heartbeatMessageDefinitionMaxBytesLength;

            /*
            0 = {uint8} 4
            1 = {uint8} 14
            2 = {uint8} 0
            3 = {uint8} 79
            4 = {uint8} 74
            5 = {uint8} 16
            6 = {uint8} 0
            7 = {uint8} 79
            8 = {uint8} 74
            9 = {uint8} 16
            10 = {uint8} 0
            11 = {uint8} 79
            12 = {uint8} 74
            13 = {uint8} 16
            14 = {uint8} 0
            15 = {uint8} 0
            16 = {uint8} 0
             */


            long l1 = 1066249L;
            byte[] bytes1 = ByteBuffer.allocate(32).order(ByteOrder.LITTLE_ENDIAN)
                    .putInt(1066249)
                    .putInt(1066249)
                    .putInt(1066249)
                    .putInt(0)
                    .putInt(0)
                    .array();


            ByteBuf data = Unpooled.wrappedBuffer(header, bytes1);
            stream.writeAndFlush(data);
            LOGGER.info("heartbeat sent");

        }
    }
}

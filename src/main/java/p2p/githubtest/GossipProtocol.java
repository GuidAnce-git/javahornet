package p2p.githubtest;

import io.libp2p.core.Stream;
import io.libp2p.protocol.ProtocolHandler;
import io.libp2p.protocol.ProtocolMessageHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Logger;

public class GossipProtocol extends ProtocolHandler<GossipController> {
    private static final Logger LOGGER = Logger.getLogger("GossipProtocol");

    @Inject
    HeartbeatMsg heartbeat;

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
            String msgStr = msg.toString(Charset.defaultCharset());

            LOGGER.info("GossipHandler onMessage, remote peerId :" + stream.remotePeerId() + " , msg :" + msgStr);
        }

        @Override
        public void onException(@Nullable Throwable throwable) {
            LOGGER.info("GossipHandler onException");
        }

        @Override
        public void onClosed(@NotNull Stream stream) {
            LOGGER.info("GossipHandler onClosed");
        }

        @Override
        public void onActivated(@NotNull Stream stream) {
            LOGGER.info("GossipHandler onActivated");
            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

            this.stream = stream;

            send();

            /*
            Runnable pinger = () -> {
                System.out.println("sending ping");
                heartbeat("ping");
            };
            ScheduledFuture<?> pingerHandle = scheduler.scheduleAtFixedRate(pinger, 1, 10, SECONDS);
            Runnable canceller = () -> pingerHandle.cancel(false);
            scheduler.schedule(canceller, 5, MINUTES);
            */

            completableFuture.complete(this);
        }

        @Override
        public void fireMessage(@NotNull Stream stream, @NotNull Object o) {
            LOGGER.info("GossipHandler fireMessage " + o.getClass().getName());
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
        public CompletableFuture<String> send(String msg) {
            LOGGER.info("send heartbeat: " + msg);
            CompletableFuture<String> ret = new CompletableFuture<>();
            ByteBuf data = Unpooled.wrappedBuffer(msg.getBytes());
            stream.writeAndFlush(data);
            ret.complete("heartbeat sent");
            return null;
        }
    }
}

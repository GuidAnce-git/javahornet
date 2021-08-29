package p2p.githubtest;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.WriteBufferWaterMark;
import io.netty.handler.timeout.WriteTimeoutException;
import io.netty.handler.timeout.WriteTimeoutHandler;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@ChannelHandler.Sharable
public class Firewall extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = Logger.getLogger("Firewall");

    private final Duration writeTimeout;

    public Firewall(Duration writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        ctx.channel().config().setWriteBufferWaterMark(new WriteBufferWaterMark(100, 1024));
        ctx.pipeline().addLast(new WriteTimeoutHandler(writeTimeout.toMillis(), TimeUnit.MILLISECONDS));
        ctx.pipeline().addLast(new FirewallExceptionHandler());
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) {
        ctx.channel().config().setAutoRead(ctx.channel().isWritable());
        ctx.fireChannelWritabilityChanged();
    }

    class FirewallExceptionHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            if (cause instanceof WriteTimeoutException) {
                LOGGER.severe("Firewall closed channel by write timeout. No writes during " + writeTimeout);
            } else {
                LOGGER.severe("Error in Firewall, disconnecting" + cause);
                FutureUtil.ignoreFuture(ctx.close());
            }
        }
    }
}

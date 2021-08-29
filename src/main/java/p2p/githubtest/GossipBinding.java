package p2p.githubtest;

import io.libp2p.core.P2PChannelHandler;
import io.libp2p.core.multistream.StrictProtocolBinding;
import org.jetbrains.annotations.NotNull;

public class GossipBinding extends StrictProtocolBinding<GossipController> {
    public GossipBinding(@NotNull String announce, @NotNull P2PChannelHandler<GossipController> protocol) {
        super(announce, protocol);
    }
}

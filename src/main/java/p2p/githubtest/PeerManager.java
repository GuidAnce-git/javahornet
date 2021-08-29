package p2p.githubtest;

import io.libp2p.core.Connection;
import io.libp2p.core.ConnectionHandler;
import io.libp2p.core.Network;
import io.libp2p.core.PeerId;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class PeerManager implements ConnectionHandler {
    private static final Logger LOGGER = Logger.getLogger("PeerManager");

    private final ConcurrentHashMap<NodeId, Peer> connectedPeerMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<NodeId, SafeFuture<Peer>> pendingConnections = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler;

    public PeerManager(ScheduledExecutorService scheduler) {
        this.scheduler = scheduler;
    }


    @Override
    public void handleConnection(@NotNull final Connection connection) {
        final PeerId remoteId = connection.secureSession().getRemoteId();
        LOGGER.info("Got new connection from " + remoteId);
        connection.closeFuture().thenRun(() -> LOGGER.info("Peer disconnected: " + remoteId));

    }

    public p2p.githubtest.SafeFuture<Peer> connect(final MultiaddrPeerAddress peer, final Network network) {
        return pendingConnections.computeIfAbsent(peer.getId(), __ -> doConnect(peer, network));
    }

    private SafeFuture<Peer> doConnect(final MultiaddrPeerAddress peer, final Network network) {
        LOGGER.info(String.format("Connecting to {%s}", peer));
        LOGGER.info("network = " + network.toString());

        return SafeFuture.of(() -> network.connect(peer.getMultiaddr()))
                .thenApply(
                        connection -> {
                            final LibP2PNodeId nodeId =
                                    new LibP2PNodeId(connection.secureSession().getRemoteId());
                            return connectedPeerMap.get(nodeId);
                        })
                .whenComplete((result, error) -> pendingConnections.remove(peer.getId()));
    }

    public Optional<Peer> getPeer(NodeId id) {
        return Optional.ofNullable(connectedPeerMap.get(id));
    }

    public int getPeerCount() {
        return connectedPeerMap.size();
    }

    public Stream<Peer> streamPeers() {
        return connectedPeerMap.values().stream();
    }

    public ScheduledExecutorService getScheduler() {
        return scheduler;
    }

    public ConcurrentHashMap<NodeId, Peer> getConnectedPeerMap() {
        return connectedPeerMap;
    }

    public ConcurrentHashMap<NodeId, SafeFuture<Peer>> getPendingConnections() {
        return pendingConnections;
    }
}
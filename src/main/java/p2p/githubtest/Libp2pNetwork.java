package p2p.githubtest;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.libp2p.core.Host;
import io.libp2p.core.PeerId;
import io.libp2p.core.crypto.KeyKt;
import io.libp2p.core.crypto.PrivKey;
import io.libp2p.core.dsl.Builder;
import io.libp2p.core.dsl.BuilderJKt;
import io.libp2p.core.multiformats.Multiaddr;
import io.libp2p.core.mux.StreamMuxerProtocol;
import io.libp2p.security.noise.NoiseXXSecureChannel;
import io.libp2p.transport.tcp.TcpTransport;
import io.netty.handler.logging.LogLevel;
import org.apache.commons.lang3.StringUtils;
import org.apache.tuweni.bytes.Bytes;

import java.math.BigInteger;
import java.net.*;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static p2p.githubtest.SafeFuture.failedFuture;

public class Libp2pNetwork implements P2PNetwork<Peer> {
    private static final Logger LOGGER = Logger.getLogger("Libp2pNetwork");

    PeerManager peerManager;
    private final Host host;
    private final PrivKey privKeyBytes;
    private final NodeId nodeId;
    private InetAddress privateAddress;
    private final int listenPort;
    private final AtomicReference<State> state = new AtomicReference<>(State.IDLE);
    private final Multiaddr advertisedAddr;


    public Libp2pNetwork(int port, String privateKey) {

        // getting local IP
        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 80);
            privateAddress = InetAddress.getByName(socket.getLocalAddress().getHostAddress());
            LOGGER.info("Local IP address set: " + privateAddress);
        } catch (SocketException | UnknownHostException e) {
            LOGGER.log(Level.SEVERE, "Getting local IP address failed", e);
            e.printStackTrace();
        }

        //set port
        listenPort = port;

        // init peer manager in single thread
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(
                new ThreadFactoryBuilder().setDaemon(true).setNameFormat("libp2p-%d").build());
        peerManager = new PeerManager(scheduler);

        // generate node ID
        Bytes pub = Bytes.fromHexString(privateKey);
        privKeyBytes = KeyKt.unmarshalPrivateKey(pub.toArrayUnsafe());
        nodeId = new LibP2PNodeId(PeerId.fromPubKey(privKeyBytes.publicKey()));
        LOGGER.info("P2P Node ID = " + nodeId);

        advertisedAddr = MultiaddrUtil.fromInetSocketAddress(new InetSocketAddress(privateAddress, listenPort), nodeId);

        host = BuilderJKt.hostJ(Builder.Defaults.None,
                b -> {
                    b.getIdentity().setFactory(() -> privKeyBytes);
                    b.getTransports().add(TcpTransport::new);
                    b.getSecureChannels().add(NoiseXXSecureChannel::new);
                    b.getMuxers().add(StreamMuxerProtocol.getMplex());
                    b.getNetwork().listen(advertisedAddr.toString());
                    b.getProtocols().add(new Gossip());
                    b.getDebug().getBeforeSecureHandler().addLogger(LogLevel.DEBUG, "wire.ciphered");
                    b.getDebug().getMuxFramesHandler().addLogger(LogLevel.DEBUG, "wire.mux");
                    b.getConnectionHandlers().add(peerManager);
                });
    }

    @Override
    public SafeFuture<?> start() {
        if (!state.compareAndSet(State.IDLE, State.RUNNING)) {
            return SafeFuture.failedFuture(new IllegalStateException("Network already started"));
        }
        LOGGER.info("Starting libp2p network...");
        return SafeFuture.of(host.start())
                .thenApply(
                        i -> {
                            LOGGER.info(getNodeAddress());
                            return null;
                        });
    }

    @Override
    public p2p.githubtest.SafeFuture<Peer> connect(final PeerAddress peer) {

        return peer.as(MultiaddrPeerAddress.class)
                .map(staticPeer -> peerManager.connect(staticPeer, host.getNetwork()))
                .orElseGet(
                        () ->
                                failedFuture(
                                        new IllegalArgumentException()));
    }


    /**
     * Parses a peer address in any of this network's supported formats.
     *
     * @param peerAddress the address to parse
     * @return a {@link PeerAddress} which is supported by {@link #connect(PeerAddress)} for
     * initiating connections
     */
    @Override
    public PeerAddress createPeerAddress(final String peerAddress) {
        return MultiaddrPeerAddress.fromAddress(peerAddress);
    }

    /**
     * Converts a {@link DiscoveryPeer} to a {@link PeerAddress} which can be used with this network's
     * {@link #connect(PeerAddress)} method.
     *
     * @param discoveryPeer the discovery peer to convert
     * @return a {@link PeerAddress} which is supported by {@link #connect(PeerAddress)} for
     * initiating connections
     */
    @Override
    public PeerAddress createPeerAddress(final DiscoveryPeer discoveryPeer) {
        return MultiaddrPeerAddress.fromDiscoveryPeer(discoveryPeer);
    }

    @Override
    public long subscribeConnect(PeerConnectedSubscriber subscriber) {
        return 0;
    }

    @Override
    public void unsubscribeConnect(long subscriptionId) {

    }

    @Override
    public boolean isConnected(final PeerAddress peerAddress) {
        return peerManager.getPeer(peerAddress.getId()).isPresent();
    }

    @Override
    public Bytes getPrivateKey() {
        return Bytes.wrap(privKeyBytes.raw());
    }

    @Override
    public Optional<Peer> getPeer(final NodeId id) {
        return peerManager.getPeer(id);
    }

    @Override
    public Stream<Peer> streamPeers() {
        return peerManager.streamPeers();
    }

    @Override
    public NodeId parseNodeId(final String nodeId) {
        return new LibP2PNodeId(PeerId.fromBase58(nodeId));
    }

    @Override
    public int getPeerCount() {
        return peerManager.getPeerCount();
    }

    @Override
    public String getNodeAddress() {
        return advertisedAddr.toString();
    }

    @Override
    public NodeId getNodeId() {
        return nodeId;
    }

    @Override
    public int getListenPort() {
        return listenPort;
    }

    /**
     * Get the Ethereum Node Record (ENR) for the local node, if one exists.
     *
     * @return the local ENR.
     */
    @Override
    public Optional<String> getEnr() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getDiscoveryAddress() {
        return Optional.empty();
    }

    //    public void start(){
//        try {
//            host.start().get();
//            log.info("Node started and listening on ");
//            log.info(host.listenAddresses().toString());
//
//            int queryInterval = 6000;
//            String serviceTag = "_ipfs-discovery._udp";
//            String serviceTagLocal = serviceTag + ".local.";
//            peerFinder = new MDnsDiscovery(host, serviceTagLocal, queryInterval,privateAddress);
//            peerFinder.getNewPeerFoundListeners().add(peerInfo -> {
//                System.out.println("find peer : " + peerInfo.getPeerId().toString());
//                Unit u = Unit.INSTANCE;
//
//                if (!peerInfo.getAddresses().toString().contains(this.getAddress()) && !knownNodes.containsKey(peerInfo.getPeerId())) {
//                    node.setPeerInfo(peerInfo);
//                    knownNodes.put(peerInfo.getPeerId(), node);
//                    peers.add(node);
//                    String ip = peerInfo.getAddresses().toString() + "/ipfs/" +
//                            peerInfo.getPeerId().toString();
//                    ip = ip.replace("[", "").replace("]", "");
//                    System.out.println("ip = " + ip);
//                    Multiaddr address = Multiaddr.fromString(ip);
//                    handler.dial(this.host, address);
//                }
//                return u;
//            });
//            peerFinder.start();
//            log.info("Peer finder started ");
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
    public static byte[] toByteArray(String hexString) {
        if (StringUtils.isEmpty(hexString)) {
            throw new IllegalArgumentException("this hexString must not be empty");
        }

        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
        for (int i = 0; i < byteArray.length; i++) {//因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }

    @Override
    public SafeFuture<?> stop() {
        if (!state.compareAndSet(State.RUNNING, State.STOPPED)) {
            return SafeFuture.COMPLETE;
        }
        LOGGER.severe("JvmLibP2PNetwork.stop()");
        return SafeFuture.of(host.stop());
    }

    public Host getHost() {
        return host;
    }

    public String getAddress() {
        return "/ip4/" + privateAddress.getHostAddress() +
                "/tcp/" + listenPort;
    }

    @FunctionalInterface
    public interface PrivateKeyProvider {
        PrivKey get();
    }

    public static String bytes2hex01(byte[] bytes) {
        /*
         * 第一个参数的解释，记得一定要设置为1
         *  signum of the number (-1 for negative, 0 for zero, 1 for positive).
         */
        BigInteger bigInteger = new BigInteger(1, bytes);
        return bigInteger.toString(16);
    }

    public PeerManager getPeerManager() {
        return peerManager;
    }

    public void setPeerManager(PeerManager peerManager) {
        this.peerManager = peerManager;
    }
}
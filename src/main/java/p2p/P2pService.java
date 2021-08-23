package p2p;

import io.libp2p.core.Host;
import io.libp2p.core.dsl.HostBuilder;
import io.libp2p.protocol.Identify;
import io.libp2p.security.noise.NoiseXXSecureChannel;
import io.libp2p.transport.tcp.TcpTransport;
import io.quarkus.runtime.Startup;
import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import p2p.githubtest.Libp2pNetwork;
import p2p.noise.protocol.HandshakeState;

import javax.inject.Singleton;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Singleton
@Startup
public class P2pService {
    private static final Logger LOGGER = Logger.getLogger("P2pService");

    Libp2pNetwork network;

    public P2pService() {
        start();
    }

    public void start() {
        network = new Libp2pNetwork();
        network.start();
    }

    public void stop() {
        network.stop();
        LOGGER.info("P2P Service stopped.");
    }


    public static void init() {
        LOGGER.info("Starting P2P Interface...");
        try (final ServerSocket serverSocket = new ServerSocket(1492); final Socket socket = serverSocket.accept()) {
            final InputStream inputStream = socket.getInputStream();

            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            final String line = bufferedReader.readLine();    // reads a line of text
            System.out.println(line);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public static void vertx() {
        LOGGER.info("Starting Vertx Interface...");

        // start Vertx TCP listener
        final Vertx vertx = Vertx.vertx();
        final NetServerOptions netServerOptions = new NetServerOptions().setPort(15600);
        final NetServer server = vertx.createNetServer(netServerOptions);

        server.connectHandler(socket -> {
            socket.handler(buffer -> {
                LOGGER.info("Received some bytes from: " + socket.remoteAddress());
                System.out.println(buffer);

                List<String> messagesList = Stream.of(buffer.toString().split("\n", -1))
                        .collect(Collectors.toList());

                // remove empty entries
                messagesList.removeAll(Arrays.asList("", null));

                // remove first char as it's not needed
                messagesList = messagesList.stream().map(e -> e.substring(1)).collect(Collectors.toList());

                // remove first char in every line if char is "/"
                List<String> lines = new ArrayList<>();
                for (String value : messagesList) {
                    if (value.startsWith("/")) {
                        value = value.substring(1);
                    }
                    if (value.startsWith("\n")) {
                        value = value.substring(1);
                    }
                    lines.add(value);
                }
                messagesList.clear();
                messagesList.addAll(lines);

                //save Strings in Map
                Map<String, String> connectionParameterMap = new HashMap<>();
                for (String value : messagesList) {
                    List<String> parameterList = new ArrayList<>();

                    if (!value.contains("/") && !value.contains(":")) {
                        connectionParameterMap.put(value, "");
                    } else if (value.contains("/")) {
                        parameterList = Arrays.stream(value.split("/")).collect(Collectors.toList());
                        connectionParameterMap.put(parameterList.get(0), parameterList.get(1));
                    } else if (value.contains(":")) {
                        parameterList = Arrays.stream(value.split(":")).collect(Collectors.toList());
                        connectionParameterMap.put(parameterList.get(0), parameterList.get(1));
                    }
                }

                if (connectionParameterMap.containsKey("multistream")) {
                    LOGGER.info("Received init package from " + socket.remoteAddress());
                    LOGGER.info("Send init tx back to " + socket.remoteAddress());
                    socket.write("\u0013/multistream/1.0.0\n" +
                            "\u001D/libp2p/simultaneous-connect\n" +
                            "\u0007/noise\n");

                } else if (connectionParameterMap.containsKey("select")) {
                    LOGGER.info("Received select package from " + socket.remoteAddress());
                    System.out.println("Sender ID: " + connectionParameterMap.get("select"));
                    long ownId = 1L;
                    String response = String.format("select:%s\n", ownId);

                    // Byte conversion to make peer happy
                    byte[] bytesFromDatabase = response.getBytes(StandardCharsets.UTF_8);
                    byte[] responseAsBytes = new byte[1];
                    byte[] combined = new byte[responseAsBytes.length + bytesFromDatabase.length];
                    responseAsBytes[0] = (byte) bytesFromDatabase.length;
                    System.arraycopy(responseAsBytes, 0, combined, 0, responseAsBytes.length);
                    System.arraycopy(bytesFromDatabase, 0, combined, responseAsBytes.length, bytesFromDatabase.length);
                    String responseAsString = new String(combined, StandardCharsets.UTF_8);

                    //socket.write(Arrays.toString(combined));
                    System.out.println("Own ID sent: " + ownId);

                    socket.write(responseAsString);


                    /*
                    // for testing
                    byte[] bytesFromDatabase2 = String.format("select:%s\n", connectionParameterMap.get("select")).getBytes();
                    byte[] responseAsBytes2 = new byte[1];
                    byte[] combined2 = new byte[responseAsBytes2.length + bytesFromDatabase2.length];
                    responseAsBytes2[0] = (byte) bytesFromDatabase2.length;
                    System.arraycopy(responseAsBytes2, 0, combined2, 0, responseAsBytes2.length);
                    System.arraycopy(bytesFromDatabase2, 0, combined2, responseAsBytes2.length, bytesFromDatabase2.length);

                    System.out.println(Arrays.toString(buffer.getBytes()));
                    System.out.println(Arrays.toString(combined2));

                     */
                } else if (connectionParameterMap.containsKey("nitiator")) {
                    LOGGER.info("Received initiator package from " + socket.remoteAddress());
                    String responseAsString = "\nresponder\n";
                    socket.write(responseAsString);
                } else if (connectionParameterMap.containsKey("noise") && connectionParameterMap.size() == 1) {
                    LOGGER.info("Received noise package from " + socket.remoteAddress());
                    String response = "/noise\n";
                    // Byte conversion to make peer happy
                    byte[] bytesFromDatabase = response.getBytes(StandardCharsets.UTF_8);
                    byte[] responseAsBytes = new byte[1];
                    byte[] combined = new byte[responseAsBytes.length + bytesFromDatabase.length];
                    responseAsBytes[0] = (byte) bytesFromDatabase.length;
                    System.arraycopy(responseAsBytes, 0, combined, 0, responseAsBytes.length);
                    System.arraycopy(bytesFromDatabase, 0, combined, responseAsBytes.length, bytesFromDatabase.length);
                    String responseAsString = new String(combined, StandardCharsets.UTF_8);

                    socket.write(responseAsString);


                } else {

                    try {
                        HandshakeState handshakeState = new HandshakeState("Noise_XX_25519_ChaChaPoly_BLAKE2s", 2);
                        handshakeState.setPrologue(buffer.getBytes(), 1, 1);
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }


                    /*
                    try {
                        NoiseHandshakePayloadProtos.NoiseHandshakePayload noiseHandshakePayload = NoiseHandshakePayloadProtos.NoiseHandshakePayload.newBuilder().mergeFrom(buffer.getBytes()).build();
                    } catch (InvalidProtocolBufferException e) {
                        e.printStackTrace();
                    }

                     */
                }






                /*
                String test = "\u0000 \fS+�(��O�d\f!&�l��~ֳ��پ<���\u007F\n" +
                        "�%"

                  S+�(��O�d!&�l��~ֳ��پ<���
�%
                 */

            });
        });

        server.listen(res -> {
            if (res.succeeded()) {
                LOGGER.info("Vertx TCP server is now listening on port: " + server.actualPort());
            } else {
                LOGGER.info("Vertx TCP server failed to bind!");
            }
        });
    }


    public static void libP2P() {

        try {
            String localListenAddress = "/ip4/127.0.0.1/tcp/15600";
            InetAddress inetAddress = InetAddress.getByName("172.0.0.1");

            Host host = new HostBuilder()
                    .transport(TcpTransport::new)
                    .secureChannel(NoiseXXSecureChannel::new)
                    .protocol(new Identify())
                    .listen(localListenAddress)
                    .build();

            host.start().get();


        } catch (final InterruptedException | ExecutionException | UnknownHostException e) {
            e.printStackTrace();
        }

    }


    private static InetAddress privateNetworkAddress() {

        try {
            final Enumeration<?> netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress inetAddress = null;
            while (netInterfaces.hasMoreElements()) {
                final NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                final Enumeration<?> e2 = ni.getInetAddresses();
                while (e2.hasMoreElements()) {
                    inetAddress = (InetAddress) e2.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.getHostAddress().contains(":")) {
                        return inetAddress;
                    }
                }
            }
        } catch (final SocketException e) {
            LOGGER.log(Level.SEVERE, "get local IP failed", e);
            e.printStackTrace();
        }
        return null;
    }


}

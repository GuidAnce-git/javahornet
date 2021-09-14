package p2p;

import io.quarkus.runtime.Startup;
import p2p.githubtest.Libp2pNetwork;

import javax.inject.Singleton;
import java.util.logging.Logger;


@Singleton
@Startup
public class P2pService {
    private static final Logger LOGGER = Logger.getLogger("P2pService");

    Libp2pNetwork libp2pNetwork;

    public P2pService() {
        start();
    }

    public void start() {
        int listenPort = 15600;
        // String prikey = "0x0802122074ca7d1380b2c407be6878669ebb5c7a2ee751bb18198f1a0f214bcb93b89411";

        String privateKey = "08011220433011af52a2ea86310c867f36530b48976484485991c014f7ec5e60f573b3fd";
        String publicKey = "080112203085e9ca8726033c643ede8cc66ccb9fd7c574a1f6ed596d543f8c216081f535";


        libp2pNetwork = new Libp2pNetwork(listenPort, privateKey);
        libp2pNetwork.start();
        LOGGER.info("P2P Service started.");
    }

    public void stop() {
        libp2pNetwork.stop();
        LOGGER.info("P2P Service stopped.");
    }

}

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
        String prikey = "0x0802122074ca7d1380b2c407be6878669ebb5c7a2ee751bb18198f1a0f214bcb93b89411";

        libp2pNetwork = new Libp2pNetwork(listenPort, prikey);
        libp2pNetwork.start();
        libp2pNetwork.getPeerCount();
        LOGGER.info("P2P Service started.");
    }

    public void stop() {
        libp2pNetwork.stop();
        LOGGER.info("P2P Service stopped.");
    }

}

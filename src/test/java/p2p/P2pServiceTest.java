package p2p;

import io.libp2p.core.Host;
import io.libp2p.core.dsl.HostBuilder;
import io.libp2p.core.multiformats.Multiaddr;
import io.libp2p.protocol.Ping;
import io.libp2p.protocol.PingController;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

@QuarkusTest
class P2pServiceTest {

    @Test
    void testPingPorts() {
        Host node = new HostBuilder().protocol(new Ping()).listen("/ip4/127.0.0.1/tcp/16600").build();

        // start listening
        try {
            node.start().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


        Multiaddr multiaddr = Multiaddr.fromString("/ip4/127.0.0.1/tcp/15600/ipfs/QmX87joyyc1hVxsrWEwtvi2ZxLfFC29ryrzD9aJ3KU6a6i");

        try {
            PingController pinger = new Ping().dial(node, multiaddr).getController().get();
            System.out.println("Sending 5 ping messages to " + multiaddr.toString());
            for (int i = 1; i <= 5; ++i) {
                long latency = pinger.ping().get();
                System.out.println("Ping " + i + ", latency " + latency + "ms");
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


    }


}
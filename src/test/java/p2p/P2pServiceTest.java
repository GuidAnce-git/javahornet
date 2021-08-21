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
        final Host host = new HostBuilder().protocol(new Ping()).listen("/ip4/127.0.0.1/tcp/15611").build();

        try {
            host.start().get();
            final Multiaddr address = Multiaddr.fromString("/ip4/127.0.0.1/tcp/15600/ipfs/Qmdmp2Do626aab3vyov9VBs2ECQk3zdd61BMhetVxWakVP");
            final PingController pinger = new Ping().dial(host, address).getController().get();
            System.out.println("Sending 5 ping messages to " + address);
            for (int i = 1; i <= 5; ++i) {
                final long latency = pinger.ping().get();
                System.out.println("Ping " + i + ", latency " + latency + "ms");
            }
            host.stop().get();
        } catch (final InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }


}
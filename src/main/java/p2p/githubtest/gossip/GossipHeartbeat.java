package p2p.githubtest.gossip;

public class GossipHeartbeat extends GossipMessageDefinition {
    public GossipHeartbeat() {
        id = MESSAGE_TYPE_HEARTBEAT;
        maxBytesLength = 4 * 3 + 2;
        setVariableLength(true);
    }
}

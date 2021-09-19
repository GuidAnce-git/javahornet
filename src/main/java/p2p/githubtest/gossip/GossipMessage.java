package p2p.githubtest.gossip;

public class GossipMessage extends GossipMessageDefinition {
    public GossipMessage() {
        id = MESSAGE_TYPE_MESSAGE;
        maxBytesLength = 32768;
        setVariableLength(true);
    }
}

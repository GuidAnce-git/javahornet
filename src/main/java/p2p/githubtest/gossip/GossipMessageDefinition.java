package p2p.githubtest.gossip;

public abstract class GossipMessageDefinition {

    public static final byte MESSAGE_TYPE_MILESTONE_REQUEST = 1;
    public static final byte MESSAGE_TYPE_MESSAGE = 2;
    public static final byte MESSAGE_TYPE_MESSAGE_REQUEST = 3;
    public static final byte MESSAGE_TYPE_HEARTBEAT = 4;

    byte id;
    int maxBytesLength;
    boolean variableLength;

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public int getMaxBytesLength() {
        return maxBytesLength;
    }

    public void setMaxBytesLength(int maxBytesLength) {
        this.maxBytesLength = maxBytesLength;
    }

    public boolean isVariableLength() {
        return variableLength;
    }

    public void setVariableLength(boolean variableLength) {
        this.variableLength = variableLength;
    }
}

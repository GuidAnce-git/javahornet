package node;


public class Node {
    private NodeOptions nodeOptions;

    public NodeOptions getNodeOptions() {
        if (nodeOptions == null) {
            return new NodeOptions();
        }
        return nodeOptions;
    }

    public void setNodeOptions(NodeOptions nodeOptions) {
        this.nodeOptions = nodeOptions;
    }
}

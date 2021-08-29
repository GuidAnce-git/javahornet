package p2p.githubtest;

public class Gossip extends GossipBinding {
    public Gossip() {
        super("/iota-gossip/1454675179895816119/1.0.0", new GossipProtocol(0, 0));
        // super("/iota-gossip/%d/1.0.0", new GossipProtocol(0, 0));
    }
}

package configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class P2p {
    public List<String> bindMultiAddresses;
    public ConnectionManager connectionManager;
    public Gossip gossip;
    public String identityPrivateKey;
    public PeerStore peerStore;
    public String reconnectInterval;
    public Autopeering autopeering;

    public List<String> getBindMultiAddresses() {
        return bindMultiAddresses;
    }

    public void setBindMultiAddresses(List<String> bindMultiAddresses) {
        this.bindMultiAddresses = bindMultiAddresses;
    }

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public Gossip getGossip() {
        return gossip;
    }

    public void setGossip(Gossip gossip) {
        this.gossip = gossip;
    }

    public String getIdentityPrivateKey() {
        return identityPrivateKey;
    }

    public void setIdentityPrivateKey(String identityPrivateKey) {
        this.identityPrivateKey = identityPrivateKey;
    }

    public PeerStore getPeerStore() {
        return peerStore;
    }

    public void setPeerStore(PeerStore peerStore) {
        this.peerStore = peerStore;
    }

    public String getReconnectInterval() {
        return reconnectInterval;
    }

    public void setReconnectInterval(String reconnectInterval) {
        this.reconnectInterval = reconnectInterval;
    }

    public Autopeering getAutopeering() {
        return autopeering;
    }

    public void setAutopeering(Autopeering autopeering) {
        this.autopeering = autopeering;
    }
}

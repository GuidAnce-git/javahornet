package p2p.githubtest.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IotaMessageDTO {
    private long networkId;
    private List<Long> parents;
    private Serializable payload;
    private long nonce;

    public long getNetworkId() {
        return networkId;
    }

    public void setNetworkId(long networkId) {
        this.networkId = networkId;
    }

    public List<Long> getParents() {
        if (parents == null) {
            return new ArrayList<>();
        }
        return parents;
    }

    public void setParents(List<Long> parents) {
        this.parents = parents;
    }

    public Serializable getPayload() {
        return payload;
    }

    public void setPayload(Serializable payload) {
        this.payload = payload;
    }

    public long getNonce() {
        return nonce;
    }

    public void setNonce(long nonce) {
        this.nonce = nonce;
    }
}

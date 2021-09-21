package p2p.githubtest.message.iota;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class IotaMessageMilestoneDTO {
    int index;
    long timestamp;
    List<byte[]> parents;
    byte[] inclusionMerkleProof;
    int nextPoWScore;
    int nextPoWScoreMilestoneIndex;
    List<byte[]> publicKeys;
    byte[] receipt;
    List<byte[]> signatures;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<byte[]> getParents() {
        if (parents == null) {
            return new ArrayList<>();
        }
        return parents;
    }

    public void setParents(List<byte[]> parents) {
        this.parents = parents;
    }

    public byte[] getInclusionMerkleProof() {
        return inclusionMerkleProof;
    }

    public void setInclusionMerkleProof(byte[] inclusionMerkleProof) {
        this.inclusionMerkleProof = inclusionMerkleProof;
    }

    public int getNextPoWScore() {
        return nextPoWScore;
    }

    public void setNextPoWScore(int nextPoWScore) {
        this.nextPoWScore = nextPoWScore;
    }

    public int getNextPoWScoreMilestoneIndex() {
        return nextPoWScoreMilestoneIndex;
    }

    public void setNextPoWScoreMilestoneIndex(int nextPoWScoreMilestoneIndex) {
        this.nextPoWScoreMilestoneIndex = nextPoWScoreMilestoneIndex;
    }

    public List<byte[]> getPublicKeys() {
        if (publicKeys == null) {
            return new ArrayList<>();
        }
        return publicKeys;
    }

    public void setPublicKeys(List<byte[]> publicKeys) {
        this.publicKeys = publicKeys;
    }

    public byte[] getReceipt() {
        return receipt;
    }

    public void setReceipt(byte[] receipt) {
        this.receipt = receipt;
    }

    public List<byte[]> getSignatures() {
        if (signatures == null) {
            return new ArrayList<>();
        }
        return signatures;
    }

    public void setSignatures(List<byte[]> signatures) {
        this.signatures = signatures;
    }
}

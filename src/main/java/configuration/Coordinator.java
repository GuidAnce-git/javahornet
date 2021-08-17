package configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinator {
    public String address;
    public int merkleTreeDepth;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMerkleTreeDepth() {
        return merkleTreeDepth;
    }

    public void setMerkleTreeDepth(int merkleTreeDepth) {
        this.merkleTreeDepth = merkleTreeDepth;
    }
}

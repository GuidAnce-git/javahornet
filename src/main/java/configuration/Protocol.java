package configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Protocol {
    public String networkID;
    public String bech32HRP;
    public double minPoWScore;
    public int milestonePublicKeyCount;
    public List<PublicKeyRanx> publicKeyRanges;

    public String getNetworkID() {
        return networkID;
    }

    public void setNetworkID(String networkID) {
        this.networkID = networkID;
    }

    public String getBech32HRP() {
        return bech32HRP;
    }

    public void setBech32HRP(String bech32HRP) {
        this.bech32HRP = bech32HRP;
    }

    public double getMinPoWScore() {
        return minPoWScore;
    }

    public void setMinPoWScore(double minPoWScore) {
        this.minPoWScore = minPoWScore;
    }

    public int getMilestonePublicKeyCount() {
        return milestonePublicKeyCount;
    }

    public void setMilestonePublicKeyCount(int milestonePublicKeyCount) {
        this.milestonePublicKeyCount = milestonePublicKeyCount;
    }

    public List<PublicKeyRanx> getPublicKeyRanges() {
        return publicKeyRanges;
    }

    public void setPublicKeyRanges(List<PublicKeyRanx> publicKeyRanges) {
        this.publicKeyRanges = publicKeyRanges;
    }
}

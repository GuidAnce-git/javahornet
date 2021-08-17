package configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pruning {
    public Milestones milestones;
    public Size size;
    public boolean pruneReceipts;

    public Milestones getMilestones() {
        return milestones;
    }

    public void setMilestones(Milestones milestones) {
        this.milestones = milestones;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public boolean isPruneReceipts() {
        return pruneReceipts;
    }

    public void setPruneReceipts(boolean pruneReceipts) {
        this.pruneReceipts = pruneReceipts;
    }
}

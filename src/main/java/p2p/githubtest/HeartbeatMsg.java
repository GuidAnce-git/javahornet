package p2p.githubtest;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HeartbeatMsg {
    int confirmedMilestoneIndex = 0;
    int pruningIndex = 0;
    int latestMilestoneIndex = 0;
    int connectedCount = 0;
    int syncedCount = 0;


    public int getConfirmedMilestoneIndex() {
        return confirmedMilestoneIndex;
    }

    public void setConfirmedMilestoneIndex(int confirmedMilestoneIndex) {
        this.confirmedMilestoneIndex = confirmedMilestoneIndex;
    }

    public int getPruningIndex() {
        return pruningIndex;
    }

    public void setPruningIndex(int pruningIndex) {
        this.pruningIndex = pruningIndex;
    }

    public int getLatestMilestoneIndex() {
        return latestMilestoneIndex;
    }

    public void setLatestMilestoneIndex(int latestMilestoneIndex) {
        this.latestMilestoneIndex = latestMilestoneIndex;
    }

    public int getConnectedCount() {
        return connectedCount;
    }

    public void setConnectedCount(int connectedCount) {
        this.connectedCount = connectedCount;
    }

    public int getSyncedCount() {
        return syncedCount;
    }

    public void setSyncedCount(int syncedCount) {
        this.syncedCount = syncedCount;
    }
}

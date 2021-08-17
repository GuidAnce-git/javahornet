package configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Requests {
    public String discardOlderThan;
    public String pendingReEnqueueInterval;

    public String getDiscardOlderThan() {
        return discardOlderThan;
    }

    public void setDiscardOlderThan(String discardOlderThan) {
        this.discardOlderThan = discardOlderThan;
    }

    public String getPendingReEnqueueInterval() {
        return pendingReEnqueueInterval;
    }

    public void setPendingReEnqueueInterval(String pendingReEnqueueInterval) {
        this.pendingReEnqueueInterval = pendingReEnqueueInterval;
    }
}

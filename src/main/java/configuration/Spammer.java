package configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Spammer {
    public String message;
    public String index;
    public String indexSemiLazy;
    public double cpuMaxUsage;
    public double mpsRateLimit;
    public int workers;
    public boolean autostart;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getIndexSemiLazy() {
        return indexSemiLazy;
    }

    public void setIndexSemiLazy(String indexSemiLazy) {
        this.indexSemiLazy = indexSemiLazy;
    }

    public double getCpuMaxUsage() {
        return cpuMaxUsage;
    }

    public void setCpuMaxUsage(double cpuMaxUsage) {
        this.cpuMaxUsage = cpuMaxUsage;
    }

    public double getMpsRateLimit() {
        return mpsRateLimit;
    }

    public void setMpsRateLimit(double mpsRateLimit) {
        this.mpsRateLimit = mpsRateLimit;
    }

    public int getWorkers() {
        return workers;
    }

    public void setWorkers(int workers) {
        this.workers = workers;
    }

    public boolean isAutostart() {
        return autostart;
    }

    public void setAutostart(boolean autostart) {
        this.autostart = autostart;
    }
}

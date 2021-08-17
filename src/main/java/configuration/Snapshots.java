package configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Snapshots {
    public int depth;
    public int interval;
    public String fullPath;
    public String deltaPath;
    public double deltaSizeThresholdPercentage;
    public List<DownloadURL> downloadURLs;

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getDeltaPath() {
        return deltaPath;
    }

    public void setDeltaPath(String deltaPath) {
        this.deltaPath = deltaPath;
    }

    public double getDeltaSizeThresholdPercentage() {
        return deltaSizeThresholdPercentage;
    }

    public void setDeltaSizeThresholdPercentage(double deltaSizeThresholdPercentage) {
        this.deltaSizeThresholdPercentage = deltaSizeThresholdPercentage;
    }

    public List<DownloadURL> getDownloadURLs() {
        return downloadURLs;
    }

    public void setDownloadURLs(List<DownloadURL> downloadURLs) {
        this.downloadURLs = downloadURLs;
    }
}

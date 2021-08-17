package configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectionManager {
    public int highWatermark;
    public int lowWatermark;

    public int getHighWatermark() {
        return highWatermark;
    }

    public void setHighWatermark(int highWatermark) {
        this.highWatermark = highWatermark;
    }

    public int getLowWatermark() {
        return lowWatermark;
    }

    public void setLowWatermark(int lowWatermark) {
        this.lowWatermark = lowWatermark;
    }
}

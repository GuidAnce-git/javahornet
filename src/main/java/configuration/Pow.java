package configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pow {
    public String refreshTipsInterval;

    public String getRefreshTipsInterval() {
        return refreshTipsInterval;
    }

    public void setRefreshTipsInterval(String refreshTipsInterval) {
        this.refreshTipsInterval = refreshTipsInterval;
    }
}

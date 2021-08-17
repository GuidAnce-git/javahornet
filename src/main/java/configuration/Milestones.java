package configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Milestones {
    public boolean enabled;
    public int maxMilestonesToKeep;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getMaxMilestonesToKeep() {
        return maxMilestonesToKeep;
    }

    public void setMaxMilestonesToKeep(int maxMilestonesToKeep) {
        this.maxMilestonesToKeep = maxMilestonesToKeep;
    }
}

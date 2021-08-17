package configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Size {
    public boolean enabled;
    public String targetSize;
    public double thresholdPercentage;
    public String cooldownTime;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getTargetSize() {
        return targetSize;
    }

    public void setTargetSize(String targetSize) {
        this.targetSize = targetSize;
    }

    public double getThresholdPercentage() {
        return thresholdPercentage;
    }

    public void setThresholdPercentage(double thresholdPercentage) {
        this.thresholdPercentage = thresholdPercentage;
    }

    public String getCooldownTime() {
        return cooldownTime;
    }

    public void setCooldownTime(String cooldownTime) {
        this.cooldownTime = cooldownTime;
    }
}

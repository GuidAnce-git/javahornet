package configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Debug {
    public String whiteFlagParentsSolidTimeout;

    public String getWhiteFlagParentsSolidTimeout() {
        return whiteFlagParentsSolidTimeout;
    }

    public void setWhiteFlagParentsSolidTimeout(String whiteFlagParentsSolidTimeout) {
        this.whiteFlagParentsSolidTimeout = whiteFlagParentsSolidTimeout;
    }
}

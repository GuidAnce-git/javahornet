package configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Warpsync {
    public int advancementRange;

    public int getAdvancementRange() {
        return advancementRange;
    }

    public void setAdvancementRange(int advancementRange) {
        this.advancementRange = advancementRange;
    }
}

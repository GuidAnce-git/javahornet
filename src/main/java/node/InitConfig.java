package node;

import java.util.ArrayList;
import java.util.List;

/**
 * InitConfig describes the result of a node initialization.
 */
public class InitConfig {
    private List<String> enabledPlugins;
    private List<String> disabledPlugins;

    public List<String> getEnabledPlugins() {
        if (enabledPlugins == null) {
            enabledPlugins = new ArrayList<>();
        }
        return enabledPlugins;
    }

    public void setEnabledPlugins(List<String> enabledPlugins) {
        this.enabledPlugins = enabledPlugins;
    }

    public List<String> getDisabledPlugins() {
        if (disabledPlugins == null) {
            disabledPlugins = new ArrayList<>();
        }
        return disabledPlugins;
    }

    public void setDisabledPlugins(List<String> disabledPlugins) {
        this.disabledPlugins = disabledPlugins;
    }
}

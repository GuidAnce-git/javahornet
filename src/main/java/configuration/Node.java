package configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Node {
    public String alias;
    public String profile;
    public List<Object> disablePlugins;
    public List<String> enablePlugins;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public List<Object> getDisablePlugins() {
        return disablePlugins;
    }

    public void setDisablePlugins(List<Object> disablePlugins) {
        this.disablePlugins = disablePlugins;
    }

    public List<String> getEnablePlugins() {
        return enablePlugins;
    }

    public void setEnablePlugins(List<String> enablePlugins) {
        this.enablePlugins = enablePlugins;
    }
}

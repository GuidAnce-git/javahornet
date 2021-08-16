package node;

import java.util.List;

public class Node {
    private List<String> disabledPlugins;
    private List<String> enabledPlugins;
    private List<CorePlugin> corePluginList;
    private NodeOptions nodeOptions;


    public List<String> getDisabledPlugins() {
        return disabledPlugins;
    }

    public void setDisabledPlugins(List<String> disabledPlugins) {
        this.disabledPlugins = disabledPlugins;
    }

    public List<String> getEnabledPlugins() {
        return enabledPlugins;
    }

    public void setEnabledPlugins(List<String> enabledPlugins) {
        this.enabledPlugins = enabledPlugins;
    }

    public List<CorePlugin> getCorePluginList() {
        return corePluginList;
    }

    public void setCorePluginList(List<CorePlugin> corePluginList) {
        this.corePluginList = corePluginList;
    }

    public NodeOptions getNodeOptions() {
        if (nodeOptions == null) {
            return new NodeOptions();
        }
        return nodeOptions;
    }

    public void setNodeOptions(NodeOptions nodeOptions) {
        this.nodeOptions = nodeOptions;
    }
}

package node;

/**
 * NodeOptions defines options for a Node.
 */
public class NodeOptions {
    private InitPlugin initPlugin;
    /*
	daemon      daemon.Daemon
	corePlugins []*CorePlugin
	plugins     []*Plugin
     */

    public InitPlugin getInitPlugin() {
        return initPlugin;
    }

    public void setInitPlugin(InitPlugin initPlugin) {
        this.initPlugin = initPlugin;
    }
}

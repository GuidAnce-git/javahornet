package node;

/**
 * Pluggable is something which extends the Node's capabilities.
 */
public class Pluggable {
    // A reference to the Node instance.
    private Node node;

    // The name of the plugin.
    private String name;

    // TODO
    //The config parameters for this plugin.
    //Params *PluginParams
    private PluginParams pluginParams;

    // TODO
    // The function to call to initialize the plugin dependencies.
    // DepsFunc interface{}

    /*
    // Provide gets called in the provide stage of node initialization.
    Provide ProvideFunc
    // Configure gets called in the configure stage of node initialization.
    Configure Callback
    // Run gets called in the run stage of node initialization.
    Run Callback

     */
}

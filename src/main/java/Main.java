import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import node.InitConfig;
import node.InitPlugin;
import node.Node;
import node.NodeOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@QuarkusMain
public class Main {
    private static final Logger LOGGER = Logger.getLogger("Main");

    public static void main(String ... args) {
        LOGGER.info("Running main method");


        init();

        Quarkus.run(args);


    }

    private static void init() {
        LOGGER.info("Main init started");

        Node node = new Node();
        node.setNodeOptions(new NodeOptions());

        // TODO - read plugins from config file
        // Fake data created
        node.getNodeOptions().setInitPlugin(new InitPlugin());

        if (node.getNodeOptions().getInitPlugin() == null) {
            LOGGER.severe("You must configure the node with an InitPlugin. Start aborted.");
            throw new RuntimeException("No InitPlugins defined.");
        }

        // TODO - clarify if needed when settings will be read from config file
        List<String> params = new ArrayList<>();
        List<String> masked = new ArrayList<>();

        // read plugins from config file
        InitConfig initConfig = new InitConfig();
        node.getEnabledPlugins().addAll(initConfig.getEnabledPlugins());
        node.getDisabledPlugins().addAll(initConfig.getDisabledPlugins());



    }
}

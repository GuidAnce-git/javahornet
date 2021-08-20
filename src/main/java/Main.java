import configuration.Configuration;
import configuration.ConfigurationBA;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import node.Node;

import java.util.logging.Logger;

@QuarkusMain
public class Main {
    private static final Logger LOGGER = Logger.getLogger("Main");


    public static void main(final String... args) {
        LOGGER.info("Running main method");
        Quarkus.run(args);
        final Configuration configuration = ConfigurationBA.readConfigJson();
        final Node node = new Node();


    }

}

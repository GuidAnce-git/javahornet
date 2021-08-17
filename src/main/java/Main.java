import com.fasterxml.jackson.databind.ObjectMapper;
import configuration.Configuration;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import node.Node;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
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

        // read config.json
        ObjectMapper mapper = new ObjectMapper();
        ClassLoader classLoader = Main.class.getClassLoader();
        try {
            Configuration configuration = mapper.readValue(new File(Objects.requireNonNull(classLoader.getResource("config.json")).getFile()), Configuration.class);
            System.out.println(configuration.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Node node = new Node();

    }
}

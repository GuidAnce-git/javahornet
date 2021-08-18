import Database.RocksDataBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import configuration.Configuration;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import node.Node;

import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

@QuarkusMain
public class Main {
    private static final Logger LOGGER = Logger.getLogger("Main");

    @Singleton
    static Configuration configuration;


    public static void main(String ... args) {
        LOGGER.info("Running main method");

        init();

        Quarkus.run(args);


    }

    private static void init() {
        LOGGER.info("Main init started");

        readConfigJson();
        initDatabase();

        Node node = new Node();

    }

    private static void readConfigJson(){
        ObjectMapper mapper = new ObjectMapper();
        ClassLoader classLoader = Main.class.getClassLoader();
        try {
            configuration = mapper.readValue(new File(Objects.requireNonNull(classLoader.getResource("config.json")).getFile()), Configuration.class);
            LOGGER.info("config.json loaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initDatabase() {
        RocksDataBase.init();
    }

}

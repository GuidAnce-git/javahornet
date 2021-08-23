package configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.runtime.Startup;

import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
@Startup
public class ConfigurationBA {
    private static final Logger LOGGER = Logger.getLogger("ConfigurationBA");

    Configuration configuration;

    public ConfigurationBA() {
        configuration = readConfigJson();
    }

    public Configuration readConfigJson() {
        final ObjectMapper objectMapper = new ObjectMapper();
        Configuration configuration = new Configuration();
        try {
            final URL resource = ConfigurationBA.class.getClassLoader().getResource("config.json");
            if (resource != null) {
                final File file = new File(resource.toURI());
                configuration = objectMapper.readValue(file, Configuration.class);
            }
            LOGGER.info("config.json loaded successfully");
            return configuration;
        } catch (final IOException | URISyntaxException e) {
            LOGGER.log(Level.SEVERE, "Read config.json failed", e);
        }
        return null;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}

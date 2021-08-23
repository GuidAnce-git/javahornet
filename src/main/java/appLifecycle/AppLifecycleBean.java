package appLifecycle;

import configuration.ConfigurationBA;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import p2p.P2pService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class AppLifecycleBean {
    private static final Logger LOGGER = Logger.getLogger("ListenerBean");

    @Inject
    ConfigurationBA ConfigurationBA;

    @Inject
    P2pService p2pService;


    void onStart(@Observes final StartupEvent ev) {
        LOGGER.info("The application is starting...");


    }

    void onStop(@Observes final ShutdownEvent ev) {

        LOGGER.info("The application is stopping...");
        p2pService.stop();
    }
}

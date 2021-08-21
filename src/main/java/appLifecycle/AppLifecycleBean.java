package appLifecycle;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import p2p.P2pService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.logging.Logger;

@ApplicationScoped
public class AppLifecycleBean {
    private static final Logger LOGGER = Logger.getLogger("ListenerBean");

    static void onStart(@Observes final StartupEvent ev) {
        LOGGER.info("The application is starting...");
        //P2pService.nextStep();
        P2pService.vertx();
    }

    static void onStop(@Observes final ShutdownEvent ev) {
        LOGGER.info("The application is stopping...");
    }
}

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

    // /dns/lzimj7afpf8f2amh.myfritz.net/tcp/15600
    // 16Uiu2HAmRfT8vNbCbvjQGsfqWUtmZvrj5y8XZXiyUz6HVSqZW8gy

    void onStart(@Observes final StartupEvent ev) {
        LOGGER.info("The application is starting...");
    }

    void onStop(@Observes final ShutdownEvent ev) {
        LOGGER.info("The application is stopping...");
        // TODO
        // //shutdown peer manager (ScheduledExecutorService scheduler)
    }
}

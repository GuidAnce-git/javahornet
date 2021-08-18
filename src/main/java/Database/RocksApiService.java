package Database;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@ApplicationScoped
public class RocksApiService {
    private static final Logger LOGGER = Logger.getLogger("RocksApiService");

    private final RocksDataBase rocksDataBase;

    public RocksApiService(RocksDataBase rocksDataBase) {
        this.rocksDataBase = rocksDataBase;
    }

    public Response save(String key, String value) {
        LOGGER.info("save");
        rocksDataBase.save(key, value);

        return Response.ok().build();
    }

    public Response get(String key) {
        LOGGER.info("find");
        String result = rocksDataBase.find(key);
        if(result == null) {
            return Response.noContent().build();
        }
        return Response.ok(result).build();

    }

}

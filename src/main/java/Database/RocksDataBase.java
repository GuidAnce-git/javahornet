package Database;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class RocksDataBase implements KeyValueRepository<String, String> {
    private static final Logger LOGGER = Logger.getLogger("RocksDataBase");

    static RocksDB db;

    public static void init(){
        LOGGER.info("RocksDB initialization started...");

        // a static method that loads the RocksDB C++ library.
        RocksDB.loadLibrary();

        // the Options class contains a set of configurable DB options
        // that determines the behaviour of the database.
        try (final Options options = new Options().setCreateIfMissing(true)) {

            // a factory method that returns a RocksDB instance
            try {
                db = RocksDB.open(options, "/rocks-db");
                LOGGER.info("RocksDB initialized and ready to use");

            } catch (RocksDBException e) {
                LOGGER.severe(String.format("Error initializing RocksDB, check configurations and permissions, exception: {%s}, message: {%s}, stackTrace: {%s}", e.getCause(), e.getMessage(), Arrays.toString(e.getStackTrace())));
            }
        }
    }


    @Override
    public void save(String key, String value) {
        LOGGER.info("save");
        try {
            db.put(key.getBytes(), value.getBytes());
        } catch (RocksDBException e) {
            LOGGER.log(Level.SEVERE, String.format("Error saving entry in RocksDB, cause: {%s}, message: {%s}", e.getCause(), e.getMessage()));
        }
    }

    @Override
    public String find(String key) {
        LOGGER.info("find");
        String result = null;
        try {
            byte[] bytes = db.get(key.getBytes());
            if(bytes == null) {
                return null;
            }
            result = new String(bytes);
        } catch (RocksDBException e) {
            LOGGER.severe(String.format("Error retrieving the entry in RocksDB from key: {%s}, cause: {%s}, message: {%s}", key, e.getCause(), e.getMessage()));
        }
        return result;
    }

    @Override
    public void delete(String key) {

    }
}

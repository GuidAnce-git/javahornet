package Database;


import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RedisDB {
    private static final Logger LOGGER = Logger.getLogger("RedisDB");

    final static HostAndPort hostAndPort = new HostAndPort("localhost", 6379);
    static final JedisPoolConfig poolConfig = buildPoolConfig();
    static JedisPool jedisPool = new JedisPool(poolConfig, hostAndPort.getHost());


    static boolean save(final String key, final String value) {
        try (final Jedis jedis = jedisPool.getResource()) {
            jedis.set(key, value);
            LOGGER.info("saved: " + key + " - " + value);
            return true;
        } catch (final Exception e) {
            LOGGER.log(Level.SEVERE, "Save to RedisDB failed.", e);
        }
        return false;
    }

    static String get(final String key) {
        try (final Jedis jedis = jedisPool.getResource()) {
            return jedis.get(key);
        }
    }


    private static JedisPoolConfig buildPoolConfig() {
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        return poolConfig;
    }
}

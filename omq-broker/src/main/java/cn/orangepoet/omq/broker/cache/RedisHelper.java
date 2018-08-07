package cn.orangepoet.omq.broker.cache;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.List;

@Component
public class RedisHelper {
    private final int port = 6379;
    private final String hostname = "192.168.1.9";

    private final Jedis jedis;

    public RedisHelper() {
        jedis = new Jedis(hostname, port);
    }

    public void rpush(String key, String... values) {
        jedis.rpush(key, values);
    }

    public List<String> lrange(String key, int start, int end) {
        return jedis.lrange(key, start, end);
    }

    public String hget(String key, String field) {
        return jedis.hget(key, field);
    }

    public void hset(String key, String field, String value) {
        jedis.hset(key, field, value);
    }
}

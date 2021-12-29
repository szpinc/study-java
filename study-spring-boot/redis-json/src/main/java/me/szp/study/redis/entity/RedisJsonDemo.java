package me.szp.study.redis.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.providers.PooledConnectionProvider;

/**
 * @author 孙志鹏
 * @since 2021/12/29 12:05 PM
 */
public class RedisJsonDemo {

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HostAndPort hostAndPort = new HostAndPort("127.0.0.1", 6379);
        PooledConnectionProvider provider = new PooledConnectionProvider(hostAndPort);
        UnifiedJedis client = new UnifiedJedis(provider);
        Student maya = new Student("Maya", "Jayavant");
        client.jsonSet("student:111", mapper.writer().writeValueAsString(maya));
        Student oliwia = new Student("Oliwia", "Jagoda");
        client.jsonSet("student:222", mapper.writer().writeValueAsString(oliwia));
    }

}

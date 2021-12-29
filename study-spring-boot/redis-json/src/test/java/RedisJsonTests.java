import me.szp.study.redis.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.providers.PooledConnectionProvider;
import redis.clients.jedis.search.*;

import java.util.List;

/**
 * @author 孙志鹏
 * @since 2021/12/29 11:40 AM
 */
public class RedisJsonTests {

    private UnifiedJedis client;

    @BeforeEach
    public void connect() {
        HostAndPort hostAndPort = new HostAndPort("127.0.0.1", 6379);
        PooledConnectionProvider provider = new PooledConnectionProvider(hostAndPort);
        client = new UnifiedJedis(provider);
    }

    @Test
    public void restore() {
        Student maya = new Student("Maya", "Jayavant");
        client.jsonSet("student:111", maya);
        Student oliwia = new Student("Oliwia", "Jagoda");
        client.jsonSet("student:111", maya);
    }


    /**
     * 创建索引
     */
    @Test
    public void createIndex() {
        Schema schema = new Schema()
                .addTextField("$.firstName", 1)
                .addTextField("$.lastName", 1);
        IndexDefinition rule = new IndexDefinition(IndexDefinition.Type.JSON)
                .setPrefixes("student:");
        client.ftCreate("student-index", IndexOptions.defaultOptions().setDefinition(rule), schema);
    }

    /**
     * 查询
     */
    @Test
    public void search() {
        Query query = new Query();
        SearchResult result = client.ftSearch("student-index", query);

        List<Document> documents = result.getDocuments();

        documents.forEach(System.out::println);
    }

}

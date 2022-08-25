package me.szp.study.es.test;

import me.szp.study.es.repository.DemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

/**
 * @author 孙志鹏
 * @since 2022/1/6 11:09 AM
 */
@SpringBootTest
public class DemoRepositoryTests {

    @Autowired
    private DemoRepository demoRepository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    public void search () {

        int size = 1000;

        long count = demoRepository.count();

        long pages = count/(long)size + 1;





        demoRepository.findAll(Pageable.ofSize(1000));
    }

}

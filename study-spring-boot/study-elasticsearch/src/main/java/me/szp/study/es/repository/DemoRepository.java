package me.szp.study.es.repository;

import me.szp.study.es.entity.Demo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author 孙志鹏
 * @since 2022/1/6 11:07 AM
 */
public interface DemoRepository extends ElasticsearchRepository<Demo,String> {
}

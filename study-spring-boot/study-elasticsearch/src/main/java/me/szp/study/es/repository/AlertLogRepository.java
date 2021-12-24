package me.szp.study.es.repository;

import me.szp.study.es.entity.AlertLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 孙志鹏
 * @since 2021/12/23 12:07 PM
 */
@Repository
public interface AlertLogRepository extends ElasticsearchRepository<AlertLog, Long> {



}

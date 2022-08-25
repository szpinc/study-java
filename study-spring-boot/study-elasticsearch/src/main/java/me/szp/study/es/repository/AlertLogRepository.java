package me.szp.study.es.repository;

import me.szp.study.es.entity.AlertLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author 孙志鹏
 * @since 2021/12/23 12:07 PM
 */
@Repository
public interface AlertLogRepository extends ElasticsearchRepository<AlertLog, Long> {

    List<AlertLog> findAllByCreateTimeBetweenOrderByCreateTime(Date startTime, Date endTime);


    Page<AlertLog> findAlertLogsByClusterCode(String clusterCode, Pageable page);
}

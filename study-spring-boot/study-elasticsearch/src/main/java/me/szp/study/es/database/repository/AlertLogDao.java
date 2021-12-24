package me.szp.study.es.database.repository;

import me.szp.study.es.database.entity.AlertLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 孙志鹏
 * @since 2021/12/23 2:13 PM
 */
@Repository
public interface AlertLogDao extends JpaRepository<AlertLogEntity, Long> {



}

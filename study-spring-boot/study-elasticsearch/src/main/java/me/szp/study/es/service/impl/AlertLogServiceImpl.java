package me.szp.study.es.service.impl;

import lombok.RequiredArgsConstructor;
import me.szp.study.es.entity.AlertLog;
import me.szp.study.es.repository.AlertLogRepository;
import me.szp.study.es.service.AlertLogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 孙志鹏
 * @since 2021/12/23 12:20 PM
 */
@Service
@RequiredArgsConstructor
public class AlertLogServiceImpl implements AlertLogService {

    private final AlertLogRepository alertLogRepository;

    @Override
    public void saveAlertLog(AlertLog alertLog) {

        alertLogRepository.save(alertLog);
    }

    @Override
    public void saveAlertLog(List<AlertLog> alertLogs) {
        alertLogRepository.saveAll(alertLogs);
    }
}

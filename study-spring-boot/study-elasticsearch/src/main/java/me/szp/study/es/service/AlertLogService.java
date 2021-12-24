package me.szp.study.es.service;

import me.szp.study.es.entity.AlertLog;

import java.util.List;

/**
 * @author 孙志鹏
 * @since 2021/12/23 12:20 PM
 */
public interface AlertLogService {

    void saveAlertLog(AlertLog alertLog);

    void saveAlertLog(List<AlertLog> alertLogs);
}

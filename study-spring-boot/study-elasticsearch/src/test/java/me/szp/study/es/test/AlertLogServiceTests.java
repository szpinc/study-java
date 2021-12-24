package me.szp.study.es.test;

import me.szp.study.es.database.entity.AlertLogEntity;
import me.szp.study.es.database.repository.AlertLogDao;
import me.szp.study.es.entity.AlertLog;
import me.szp.study.es.service.AlertLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 孙志鹏
 * @since 2021/12/23 12:24 PM
 */
@SpringBootTest
class AlertLogServiceTests {

    @Autowired
    private AlertLogService alertLogService;
    @Autowired
    private AlertLogDao alertLogDao;

    @Test
    void save() {
        AlertLog alertLog = new AlertLog();
        alertLog.setId(1L);
        alertLog.setAlertRuleId(292L);
        alertLog.setAlertLevel(1);
        alertLog.setClusterCode("cluster-9ZdOccDq");
        alertLog.setInstance("172-16-14-224");

        alertLogService.saveAlertLog(alertLog);
        assert true;
    }

    @Test
    void saveAlertLog2Es() {

        int index = 1;

        int size = 500;

        while (true) {
            PageRequest pageRequest = PageRequest.of(index, size);
            Page<AlertLogEntity> page = alertLogDao.findAll(pageRequest);

            List<AlertLogEntity> list = page.getContent();

            List<AlertLog> alertLogList = list
                    .stream()
                    .map(e -> {
                        AlertLog alertLog = new AlertLog();
                        BeanUtils.copyProperties(e, alertLog);
                        return alertLog;
                    })
                    .collect(Collectors.toList());

            alertLogService.saveAlertLog(alertLogList);

            if (list.size() < 500) {
                break;
            }
            index++;
        }
    }

}

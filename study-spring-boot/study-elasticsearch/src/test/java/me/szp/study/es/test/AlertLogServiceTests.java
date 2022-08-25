package me.szp.study.es.test;

import lombok.extern.slf4j.Slf4j;
import me.szp.study.es.database.entity.AlertLogEntity;
import me.szp.study.es.database.repository.AlertLogDao;
import me.szp.study.es.entity.AlertLog;
import me.szp.study.es.repository.AlertLogRepository;
import me.szp.study.es.service.AlertLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 孙志鹏
 * @since 2021/12/23 12:24 PM
 */
@SpringBootTest
@Slf4j
class AlertLogServiceTests {

    @Autowired
    private AlertLogService alertLogService;
    @Autowired
    private AlertLogDao alertLogDao;
    @Autowired
    private AlertLogRepository alertLogRepository;

    @Test
    void getCount () {
        System.out.println(alertLogRepository.count());
    }

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

    @Test
    public void search() {
        Page<AlertLog> page = alertLogRepository.findAlertLogsByClusterCode("cluster-9ZdOccDq", Pageable.ofSize(50));

        page.forEach(alertLog -> log.info("LogInfo:{}", log));
    }

    @Test
    public void pageList() {
        Page<AlertLog> page = alertLogRepository.findAll(Pageable.ofSize(10));
        page.forEach(alertLog -> log.info("AlertLog:{}", alertLog));
    }

    private Date getDate(LocalDateTime dateTime) {
        long epochSecond = dateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
        return new Date(epochSecond);
    }

}

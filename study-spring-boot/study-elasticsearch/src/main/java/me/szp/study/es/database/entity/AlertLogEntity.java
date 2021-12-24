package me.szp.study.es.database.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 孙志鹏
 * @since 2021/12/23 12:15 PM
 */
@Entity(name = "bus_alert_log")
@Data
public class AlertLogEntity implements Serializable {

    private static final long serialVersionUID = -3231956696617408183L;

    @Id
    private Long id;

    private Long alertRuleIndicatorId;

    private Long alertRuleId;

    private Integer alertLevel;

    private Integer alertType;
    private String clusterCode;

    private String instance;

    private Long instanceId;


    private String instanceName;


    private String setThreshold;


    private String actualThreshold;


    private String alertMsg;

    private Date startsAt;

    private Date createTime;

}

package me.szp.study.es.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @author 孙志鹏
 * @since 2022/1/6 11:04 AM
 */
@Document(indexName = "kibana_sample_data_ecommerce")
@Data
public class Demo {
    @Field("_id")
    @Id
    private String id;

    @Field("customer_full_name")
    private String customerFullName;

    @Field("order_id")
    private String orderId;

}

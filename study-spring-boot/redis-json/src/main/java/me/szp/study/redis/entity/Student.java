package me.szp.study.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 孙志鹏
 * @since 2021/12/29 11:41 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {

    private String firstName;
    private String lastName;

}

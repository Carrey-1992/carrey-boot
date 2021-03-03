package com.carrey.carrey.transaction;

import lombok.Data;

import java.util.Date;

/**
 * @author Conway
 * @className User
 * @description
 * @date 2020/11/20 4:41 下午
 */
@Data
public class User {

    private Long id;

    private String name;

    private Integer age;

    private String address;

    private Date createdTime;

    private Date updatedTime;
}

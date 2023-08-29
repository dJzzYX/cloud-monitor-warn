package com.bim.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Employee implements Serializable {
    private Long id;
    private String username;
    private String name;
    private String password;
    private String phone;
    private String sex;
    private String idNumber;
    private Integer status;

    private LocalDateTime createTime;//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private  Long createUser;
    private  Long updateUser;
}

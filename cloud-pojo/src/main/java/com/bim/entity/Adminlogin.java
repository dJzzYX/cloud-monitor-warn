package com.bim.entity;

import lombok.Data;

import java.io.Serializable;

@Data
//用户登录实体类
public class Adminlogin implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String password;
    private Integer  status;

}

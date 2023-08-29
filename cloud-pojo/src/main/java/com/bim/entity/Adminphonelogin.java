package com.bim.entity;

import lombok.Data;
import org.bouncycastle.util.test.FixedSecureRandom;

import java.io.Serializable;

@Data
public class Adminphonelogin implements Serializable {
    private Long id;

    private String name;

    private String phone;

    //0男1女
    private String sex;

    private String avatar;

    private Integer  status;

}

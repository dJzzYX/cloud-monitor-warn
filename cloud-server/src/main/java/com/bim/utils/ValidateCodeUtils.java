package com.bim.utils;

import java.util.Random;

/**
 * 生成四位或六位数字验证码
 */
public class ValidateCodeUtils {
    public static Integer generateValidateCode(int length){
        Integer code =null;
        if(length ==4){
            code=new Random().nextInt(9999);
            if(code < 1000){
                code=code+1000;
            }
        }else if(length ==6){
            code = new Random().nextInt(999999);
            if(code<100000){
                code=code+1000;
            }
        }else{
            throw new RuntimeException("只能生成四位或六位验证码");
        }
        return code;
    }


    /**
     * 生成随机长度字符串验证码
     * @param length
     * @return
     */
    public static  String generateValidateCode4String(int length){
        Random rdm =new Random();
        String hash1=Integer.toHexString(rdm.nextInt());
        String capstr=hash1.substring(0,length);
        return capstr;
    }
}

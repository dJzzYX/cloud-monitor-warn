package com.bim.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bim.result.Result;
import com.bim.entity.Adminphonelogin;
import com.bim.service.AdminphoneloginService;
import com.bim.utils.ValidateCodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/adminphonelogin")
@Api(tags="管理员手机验证码登录相关接口")
@Slf4j
public class AdminphoneloginController {
    @Autowired
    private AdminphoneloginService adminphoneloginService;


    /**
     * 发送手机验证码
     * @param adminphonelogin
     * @return
     */
    @PostMapping("/sendMsg")
    @ApiOperation(value = "发送手机验证码")
    public Result<String> sendMsg(@RequestBody Adminphonelogin adminphonelogin, HttpSession session){

        String phone = adminphonelogin.getPhone();

        if(StringUtils.isNotEmpty(phone)){
            //生成随机四位验证码
            String code=ValidateCodeUtils.generateValidateCode(4).toString();

            log.info("code={}",code);

            //TODO 调用阿里云API



            session.setAttribute(phone,code);

            return Result.success("手机验证码发送成功");
        }

        return Result.error("手机验证码发送失败");
    }

    /**
     *验证码登录
     * @param map
     * @param session
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "手机验证码登录")
    public Result<Adminphonelogin> login(@RequestBody Map map, HttpSession session) {
        log.info(map.toString());

        String phone =map.get("phone").toString();

        String code =map.get("code").toString();

        Object codeInSession = session.getAttribute(phone);

        if(codeInSession !=null &&codeInSession.equals(code)){
            LambdaQueryWrapper<Adminphonelogin> queryWrapper=new LambdaQueryWrapper<>();
            queryWrapper.eq(Adminphonelogin::getPhone,phone);

            Adminphonelogin adminphonelogin = adminphoneloginService.getOne(queryWrapper);
            if(adminphonelogin ==null){
                adminphonelogin =new Adminphonelogin();
                adminphonelogin.setPhone(phone);
                adminphonelogin.setStatus(1);
                adminphoneloginService.save(adminphonelogin);
            }
            session.setAttribute("userphonelogin", adminphonelogin.getId());
            return Result.success(adminphonelogin);
        }
        return Result.error("登录失败");

    }
}

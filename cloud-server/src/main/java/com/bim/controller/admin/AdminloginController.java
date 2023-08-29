package com.bim.controller.admin;

import com.bim.result.Result;
import com.bim.entity.Adminlogin;
import com.bim.service.AdminloginService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


//用户登录表现层
@Slf4j
@RestController
@RequestMapping("/adminlogin")
@Api(tags="管理员账号密码相关接口")
public class AdminloginController { @Autowired
private AdminloginService adminloginService;

    /**
     * 员工登录
     * @param request
     * @param userlogin
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value="管理员账号密码登录")
    public Result<Adminlogin> login(HttpServletRequest request, @RequestBody Adminlogin userlogin){

        String password = userlogin.getPassword();
        DigestUtils.md5DigestAsHex(password.getBytes());
        LambdaQueryWrapper<Adminlogin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Adminlogin::getUsername,userlogin.getUsername());
        Adminlogin use= adminloginService.getOne(queryWrapper);
        if(use==null){
            return Result.error("账户名不存在或密码错误");
        }
        if(!use.getPassword().equals(password)){
            return Result.error("账户名不存在或密码错误");
        }
        if(use.getStatus()==0) {
            return Result.error("账号已禁用");
        }
            request.getSession().setAttribute("userlogin",use.getId());
        return null;
    }

    /**
     * 员工退出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation(value="管理员退出")
    public Result<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("userlogin");
        return Result.success("退出成功");
    }

}

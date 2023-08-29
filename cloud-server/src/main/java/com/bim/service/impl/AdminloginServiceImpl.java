package com.bim.service.impl;


import com.bim.entity.Adminlogin;
import com.bim.mapper.AdminloginMapper;
import com.bim.service.AdminloginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

//用户登录接口实现类
@Service
public class AdminloginServiceImpl extends ServiceImpl<AdminloginMapper, Adminlogin> implements AdminloginService {
}

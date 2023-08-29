package com.bim.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bim.dto.EmployeeDTO;
import com.bim.dto.EmployeeLoginDTO;
import com.bim.entity.Employee;

import javax.security.auth.login.AccountNotFoundException;

public interface EmployeeService  {
    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO) throws AccountNotFoundException;

    /**
     * 员工添加
     */
     void save(EmployeeDTO employeeDTO);
}

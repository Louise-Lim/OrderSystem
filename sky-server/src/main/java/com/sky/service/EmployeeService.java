package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    void save(EmployeeDTO employeeDTO);

    /**
     * Employee List Pagination
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * Enable and Disable employee account status
     * @param status
     * @param id
     */
    void employeeAccountStatus(Integer status, Long id);

    /**
     * Get employee details by id
     * @return
     */
    Employee getEmployeeDetailsById(Long id);

    /**
     * Update/Edit employee details
     * @param employeeDTO
     */
    void updateEmployee(EmployeeDTO employeeDTO);
}

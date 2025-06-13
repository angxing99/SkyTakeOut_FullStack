package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * Employee Login
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);


    /**
     * Create Employee
     * @param employeeLoginDTO
     */
    void createEmployee(EmployeeDTO employeeLoginDTO);


    /**
     * Employee query by page
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * Enable or Disable Employee
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);


    /**
     * Get Employee By Id
     * @param id
     * @return
     */
    Employee getEmployeeById(Long id);

    /**
     * Update Employee
     * @param employeeDTO
     */
    void updateEmployee(EmployeeDTO employeeDTO);
}

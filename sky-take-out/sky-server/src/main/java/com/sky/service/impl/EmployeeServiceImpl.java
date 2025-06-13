package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.PasswordConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.exception.AccountLockedException;
import com.sky.exception.AccountNotFoundException;
import com.sky.exception.PasswordErrorException;
import com.sky.mapper.EmployeeMapper;
import com.sky.result.PageResult;
import com.sky.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * login
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1. retrieve employee with user name
        Employee employee = employeeMapper.getByUsername(username);

        //2. Employee not found, throw error
        if (employee == null) {
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }


        //** Hash it with md5
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        if (!password.equals(employee.getPassword())) {
            // wrong password
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            // Account locked
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、Return employee
        return employee;
    }

    /**
     * Create employee
     *
     * @param employeeDTO
     */
    public void createEmployee(EmployeeDTO employeeDTO) {
        System.out.println("current thread id: " + Thread.currentThread().getId());

        Employee newEmployee = new Employee();

        //** Duplicate properties from DTO
        BeanUtils.copyProperties(employeeDTO, newEmployee);

        newEmployee.setStatus(StatusConstant.ENABLE);
        //** Set default password for user, TEMP set as 123456
        newEmployee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));


        employeeMapper.insert(newEmployee);

    }

    /**
     * Employee query by page
     *
     * @param employeePageQueryDTO
     * @return
     */
    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        //** example query, select * from employee limit 0,10
        //** can direct use page helper (library), no need merge query
        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());

        Page<Employee> page = employeeMapper.pageQuery(employeePageQueryDTO);
        List<Employee> records = page.getResult();
        return new PageResult(page.getTotal(), records);
    }

    /**
     * Enable or Disable Employee
     *
     * @param status
     * @param id
     */
    public void startOrStop(Integer status, Long id) {
        //** update employee set status = ? where id = ?

        Employee employee = Employee.builder()
                .status(status)
                .id(id)
                .build();


        employeeMapper.update(employee);

    }

    /**
     * Get Employee By Id
     *
     * @param id
     * @return
     */
    public Employee getEmployeeById(Long id) {
        //Retrieve employee by id
        Employee employee = employeeMapper.getEmployeeById(id);
        employee.setPassword("****");

        return employee;
    }

    @Override
    public void updateEmployee(EmployeeDTO employeeDTO) {
        Employee newEmployee = new Employee();

        //** Duplicate properties from DTO
        BeanUtils.copyProperties(employeeDTO, newEmployee);


        employeeMapper.update(newEmployee);

    }

}

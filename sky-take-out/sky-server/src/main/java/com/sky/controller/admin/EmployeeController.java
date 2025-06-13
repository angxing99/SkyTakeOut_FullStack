package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Employee Management
 */
@RestController()
@RequestMapping("/admin/employee")
@Slf4j
public class EmployeeController {

    @Autowired   //dependency injection
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    @GetMapping("/getAllEmployee")
    @ApiOperation(value = "Get All Employee Login")
    public Result<PageResult> getAllEmployee(EmployeePageQueryDTO employeePageQueryDTO) {

        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);

        return Result.success(pageResult);
    }



    /**
     * Login
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "Employee Login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("Employee Login：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //Generate JWT token
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }



    /**
     * logout
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation(value = "Employee Logout")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * Create Employee
     *
     * @param employeeDTO
     * @return
     */
    @PostMapping
    @ApiOperation(value = "Create Employee")
    public Result<String> save(@RequestBody EmployeeDTO employeeDTO) {

        employeeService.createEmployee(employeeDTO);
        return Result.success();
    }


    /**
     * Employee query by page
     *
     * @param employeePageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "Employee Query By Page")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO) {

        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);

        return Result.success(pageResult);
    }


    /**
     * Enable or Disable Employee
     *
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation(value = "Enable or Disable Employee")
    public Result startOrStop(@PathVariable Integer status, Long id) {

        employeeService.startOrStop(status, id);

        return Result.success();
    }


    /**
     * Get Employee By ID
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Get Employee By Id")
    public Result<Employee> getEmployeeById(@PathVariable Long id) {

        Employee employee = employeeService.getEmployeeById(id);

        return Result.success(employee);
    }

    @PutMapping()
    @ApiOperation(value = "Update Employee")
    public Result updateEmployee(@RequestBody EmployeeDTO employeeDTO) {

        employeeService.updateEmployee(employeeDTO);

        return Result.success();
    }


}

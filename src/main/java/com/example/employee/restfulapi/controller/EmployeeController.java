package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    public static final String EMPLOYEE_URL_BASE = "/employees";
    //在此处完成Employee API

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Get employee list
     *
     * @return employee list
     */
    @GetMapping(value = EMPLOYEE_URL_BASE)
    public List<Employee> list() {
        return employeeRepository.findAll();
    }

    /**
     * Get male employee list
     *
     * @return employee male list
     */
    @GetMapping(value = EMPLOYEE_URL_BASE +"/male")
    public List<Employee> listMale() {
        return employeeRepository.findAllByGender("male");
    }

    @GetMapping(value = EMPLOYEE_URL_BASE + "/page/{page}/pageSize/{pageSize}")
    public List<Employee> listPage(@PathVariable Integer page,
                                   @PathVariable Integer pageSize) {
        return employeeRepository.findAll(new PageRequest(page, pageSize)).getContent();
    }

    /**
     * Get a employee by id
     *
     * @param id employee id
     * @return employee
     */
    @GetMapping(value = EMPLOYEE_URL_BASE + "/{id}")
    public Employee get(@PathVariable Long id) {
        return employeeRepository.findOne(id);
    }

    /**
     * Add a employee
     *
     * @param employee
     * @return added employee
     */
    @PostMapping(value = EMPLOYEE_URL_BASE)
    public Employee add(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * Update a employee
     *
     * @param id employee id
     * @param employee new employee data
     * @return updated employee
     */
    @PutMapping(value = EMPLOYEE_URL_BASE + "/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee employee) {
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    /**
     * Delete a employee
     *
     * @param id employee id
     * @return empty string
     */
    @DeleteMapping(value = EMPLOYEE_URL_BASE + "/{id}")
    public String delete(@PathVariable Long id) {
        employeeRepository.delete(id);
        return "";
    }
}

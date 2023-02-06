package com.quinbay.email.controller;


import com.quinbay.email.api.EmployeeService;
import com.quinbay.email.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/addEmployeeDetails")
    public String addEmployeeDetails(@RequestBody Employee employee){
        return employeeService.addEmployeeDetails(employee);
    }

}

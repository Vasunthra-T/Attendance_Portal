package com.quinbay.simulator.controller;

import com.quinbay.simulator.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;


    @GetMapping("showEmployee")
    public List<String> showEmployee(){
        return employeeService.showEmployee();
    }
}

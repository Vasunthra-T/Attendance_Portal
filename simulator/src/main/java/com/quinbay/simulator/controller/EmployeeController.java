package com.quinbay.simulator.controller;

import com.quinbay.simulator.api.EmployeeService;
import com.quinbay.simulator.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("addEmployee")
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee){
        String result = employeeService.addEmployee(employee);
        HttpStatus status = (result.equals("Employee details added successfully")) ? HttpStatus.OK : HttpStatus.CONFLICT;
        return new ResponseEntity<>(result, status);
    }
}

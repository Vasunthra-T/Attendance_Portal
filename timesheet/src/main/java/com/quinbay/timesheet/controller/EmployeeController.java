package com.quinbay.timesheet.controller;

import com.quinbay.timesheet.api.EmployeeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
    @Autowired
    EmployeeInterface employeeInterface;

    @PostMapping("/authUser")
    public ResponseEntity<Object> authUser(@RequestParam String email, @RequestParam String password){
        return employeeInterface.authUser(email,password);
    }

    @GetMapping("getEmpDetails")
    public ResponseEntity<String> getDetails(){
        return employeeInterface.getDetails();
    }
}

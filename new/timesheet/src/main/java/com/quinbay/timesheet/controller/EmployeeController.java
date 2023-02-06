package com.quinbay.timesheet.controller;

import com.quinbay.timesheet.api.EmployeeService;
import com.quinbay.timesheet.model.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/authUser")
    public ResponseEntity<Object> authUser(@RequestBody EmployeeRequest employeeRequest){
        Object result = employeeService.authUser(employeeRequest);
        HttpStatus status = (result.equals("Username or password is incorrect")) ? HttpStatus.UNAUTHORIZED : HttpStatus.OK;
        return new ResponseEntity<>(result, status);
    }

    @GetMapping("getEmpDetails")
    public ResponseEntity<String> getDetails(){
        String result  = employeeService.getDetails();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/hierarchyMapping")
    public String changeManager(@RequestParam String empCode,@RequestParam String managerId){
        return employeeService.changeManager(empCode,managerId);
    }

}

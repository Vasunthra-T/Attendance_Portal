package com.quinbay.timesheet.controller;

import com.quinbay.timesheet.api.EmployeeService;
import com.quinbay.timesheet.model.Employee;
import com.quinbay.timesheet.model.LoginRequest;
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
    public ResponseEntity<Object> authUser(@RequestBody LoginRequest loginRequest){
        Object result = employeeService.authUser(loginRequest);
        HttpStatus status = (result.equals("Username or password is incorrect")) ? HttpStatus.UNAUTHORIZED : HttpStatus.OK;
        return new ResponseEntity<>(result, status);
    }

    @PutMapping("/hierarchyMapping")
    public String changeManager(@RequestParam String empCode,@RequestParam String managerId){
        return employeeService.changeManager(empCode,managerId);
    }

    @PostMapping("addEmployee")
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee){
        String result = employeeService.addEmployee(employee);
        HttpStatus status = (result.equals("Employee details added successfully")) ? HttpStatus.OK : HttpStatus.CONFLICT;
        return new ResponseEntity<>(result, status);
    }

    //    @GetMapping("getEmpDetails")
//    public ResponseEntity<String> getDetails(){
//        String result  = employeeService.getDetails();
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }


}

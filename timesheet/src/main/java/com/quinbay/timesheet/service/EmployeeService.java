package com.quinbay.timesheet.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quinbay.timesheet.api.EmployeeInterface;
import com.quinbay.timesheet.model.Employee;
import com.quinbay.timesheet.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements EmployeeInterface {
    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public ResponseEntity<Object> authUser(String email, String password) {
        try {
            Optional<Employee> opt = employeeRepository.findByEmailAndPassword(email, password);
            opt.get().setPassword("****");

            if (opt.isPresent()) {


                return new ResponseEntity<Object>(opt,HttpStatus.OK);
            } else {
                String message ="Username or password is incorrect";
                return new ResponseEntity<Object>(message,HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            String message ="Username or password is incorrect";
            return new ResponseEntity<Object>(message,HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<String> getDetails() {
        List<Employee> employees = employeeRepository.findAll();
        List<List<String>> myList = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";

        try {
            for (Employee employee : employees) {
                List<String> list1 = new ArrayList<>();

                list1.add(employee.getEmpCode());
                list1.add(employee.getEmpName());
                myList.add(list1);
            }
             jsonString = mapper.writeValueAsString(myList);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>(jsonString,HttpStatus.OK);
    }
}

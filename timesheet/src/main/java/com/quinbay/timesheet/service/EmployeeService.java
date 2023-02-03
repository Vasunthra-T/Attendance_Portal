package com.quinbay.timesheet.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quinbay.timesheet.api.EmployeeInterface;
import com.quinbay.timesheet.model.Employee;
import com.quinbay.timesheet.model.EmployeeRequest;
import com.quinbay.timesheet.model.EmployeeResponse;
import com.quinbay.timesheet.model.Timesheet;
import com.quinbay.timesheet.repository.EmployeeRepository;
import com.quinbay.timesheet.repository.TimesheetRepository;
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

    @Autowired
    TimesheetRepository timesheetRepository;


    @Override
    public Object authUser(EmployeeRequest employeeRequest) {
        try {
            Optional<Employee> opt = employeeRepository.findByEmailAndPassword(employeeRequest.getEmail(), employeeRequest.getPassword());

            if (opt.isPresent()) {
                EmployeeResponse employeeResponse = new EmployeeResponse(opt.get().getEmpName(),opt.get().getEmpCode(),opt.get().getPhone(),
                        opt.get().getEmail(),opt.get().getManagerId(),opt.get().getRole());
                return employeeResponse;
            } else {
                String message ="Username or password is incorrect";
                return message;
            }
        } catch (Exception e) {
            String message ="Username or password is incorrect";
            return message;
        }
    }

    @Override
    public String getDetails() {
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
        return jsonString;
    }

    @Override
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @Override
    public String changeManager(String empCode,String managerId){
        Optional<Employee> changeManagerEmployee = employeeRepository.findByEmpCode(empCode);

        List<Timesheet> changeManagerTimesheet = timesheetRepository.findByEmpCode(empCode);

        if(changeManagerEmployee.isPresent()){
            changeManagerEmployee.get().setManagerId(managerId);
            employeeRepository.save(changeManagerEmployee.get());
        }
        for(Timesheet t : changeManagerTimesheet){
            t.setManagerId(managerId);
            timesheetRepository.save(t);
        }
        return "Manager updated successfully";
    }
}

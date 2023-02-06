package com.quinbay.timesheet.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quinbay.timesheet.api.EmployeeService;
import com.quinbay.timesheet.model.Employee;
import com.quinbay.timesheet.model.LoginRequest;
import com.quinbay.timesheet.model.LoginResponse;
import com.quinbay.timesheet.model.Timesheet;
import com.quinbay.timesheet.repository.EmployeeRepository;
import com.quinbay.timesheet.repository.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TimesheetRepository timesheetRepository;


    @Override
    public Object authUser(LoginRequest loginRequest) {
        try {
            Optional<Employee> opt = employeeRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());

            if (opt.isPresent()) {
                LoginResponse loginResponse = new LoginResponse(opt.get().getEmpName(),opt.get().getEmpCode(),opt.get().getPhone(),
                        opt.get().getEmail(),opt.get().getManagerId(),opt.get().getRole());
                return loginResponse;
            } else {
                String message ="Username or password is incorrect";
                return message;
            }
        } catch (Exception e) {
            return e;
        }
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

    @Override
    public String addEmployee(Employee employee) {
        Optional<Employee>  emp = employeeRepository.findByEmpCode(employee.getEmpCode());
        if(emp.isPresent()){
            return "Employee is already there in database";
        }
        else {
            employeeRepository.save(employee);
            return "Employee details added successfully";
        }
    }
    //    @Override
//    public String getDetails() {
//        List<Employee> employees = employeeRepository.findAll();
//        List<List<String>> myList = new ArrayList<>();
//
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString="";
//
//        try {
//            for (Employee employee : employees) {
//                List<String> list1 = new ArrayList<>();
//
//                list1.add(employee.getEmpCode());
//                list1.add(employee.getEmpName());
//                myList.add(list1);
//            }
//             jsonString = mapper.writeValueAsString(myList);
//
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return jsonString;
//    }
}

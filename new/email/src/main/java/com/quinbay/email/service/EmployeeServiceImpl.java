package com.quinbay.email.service;

import com.quinbay.email.api.EmployeeService;
import com.quinbay.email.model.Employee;
import com.quinbay.email.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public String addEmployeeDetails(Employee employee){
        employeeRepository.save(employee);
        return "Employee details added successfully";
    }
}

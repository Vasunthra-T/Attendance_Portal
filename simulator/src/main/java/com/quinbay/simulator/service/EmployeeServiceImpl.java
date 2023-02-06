package com.quinbay.simulator.service;

import com.quinbay.simulator.api.EmployeeService;
import com.quinbay.simulator.model.Employee;
import com.quinbay.simulator.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<String> showEmployee(){
        List<Employee> employees = employeeRepository.findAll();
        List<String> myList = new ArrayList<>();
        for (Employee employee : employees) {
            myList.add(employee.getEmpCode());
        }
        return myList;
   }

    @Override
    public String addEmployee(Employee employee) {
        Optional<Employee> emp = employeeRepository.findByEmpCode(employee.getEmpCode());
        if(emp.isPresent()){
            return "Employee is already there in database";
        }
        else {
            employeeRepository.save(employee);
            return "Employee details added successfully";
        }
    }


}

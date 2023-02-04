package com.quinbay.simulator.service;

import com.quinbay.simulator.api.EmployeeInterface;
import com.quinbay.simulator.model.Employee;
import com.quinbay.simulator.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeInterface {
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


}

package com.quinbay.simulator.api;

import com.quinbay.simulator.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<String> showEmployee();
    String addEmployee(Employee employee);

}

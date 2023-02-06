package com.quinbay.timesheet.api;

import com.quinbay.timesheet.model.Employee;
import com.quinbay.timesheet.model.LoginRequest;

import java.util.List;

public interface EmployeeService {
   // String getDetails();

    Object authUser(LoginRequest loginRequest);

    String changeManager(String empCode,String managerId);

    List<Employee> getAllEmployees();

    String addEmployee(Employee employee);


}

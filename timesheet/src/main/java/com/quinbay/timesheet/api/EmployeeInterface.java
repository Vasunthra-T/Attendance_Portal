package com.quinbay.timesheet.api;

import com.quinbay.timesheet.model.Employee;
import com.quinbay.timesheet.model.EmployeeRequest;
import java.util.List;

public interface EmployeeInterface {
    String getDetails();

    Object authUser(EmployeeRequest employeeRequest);


    String changeManager(String empCode,String managerId);

    List<Employee> getAllEmployees();


}

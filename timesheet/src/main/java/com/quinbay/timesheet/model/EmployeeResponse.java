package com.quinbay.timesheet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeResponse {
    String empName;
    String empCode;
    Long phone;
    String email;
    String managerId;
    String role;
}

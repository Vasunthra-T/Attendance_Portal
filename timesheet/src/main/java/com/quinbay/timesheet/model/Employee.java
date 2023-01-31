package com.quinbay.timesheet.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="employee")
public class Employee{
    @Id
    Integer id;
    String empName;
    String empCode;
    Long phone;
    String email;
    String managerId;
    String role;
    String password;
}

package com.quinbay.timesheet.model;


import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="employee")
public class Employee{
    @Id
    @SequenceGenerator(name = "my_key", sequenceName = "my_key", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_key")
    Integer id;
    String empName;
    String empCode;
    Long phone;
    String email;
    String managerId;
    String role;
    String password;
}

package com.quinbay.simulator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employee")
public class Employee {
    @Id
    int id;
    String empName;
    String empCode;
    long phone;
    String email;
    String managerId;
    String role;
    String password;
}

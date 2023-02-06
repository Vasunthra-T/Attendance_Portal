package com.quinbay.email.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @SequenceGenerator(name = "myseq", sequenceName = "myseq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "myseq")
    Integer id;

    @Column(name="emp_code")
    String empCode;
    String email;
}

package com.quinbay.simulator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="hours")
public class Hours implements Serializable {
    @Id
    @SequenceGenerator(name = "my_seq", sequenceName = "my_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    Integer id;

    @Column(name = "emp_code")
    String empCode;


    @Column(name = "working_date")
    Date workingDate;


    @Column(name = "actual_hours")
    Double actualHours;

    public Hours(String empCode, Date workingDate, Double actualHours){
        this.empCode = empCode;
        this.workingDate = workingDate;
        this.actualHours = actualHours;
    }

}

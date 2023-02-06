package com.quinbay.email.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TimesheetApproval {
    @Id
    @SequenceGenerator(name = "myseq", sequenceName = "myseq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "myseq")
    Integer id;

    @Column(name = "emp_code")
    String empCode;

    @Column(name = "emp_name")
    String empName;

    @Column(name = "working_date")
    LocalDate workingDate;

    @Column(name = "productive_hours")
    Double productiveHours;

    @Column(name = "in_type")
    Timesheet.InType inType;

    @Column(name = "status")
    Approval.Status status;

//    public TimesheetApproval(String empCode, String empName, LocalDate workingDate, Double productiveHours, Timesheet.InType inType,  Approval.Status status) {
//        this.empCode = empCode;
//        this.empName = empName;
//        this.workingDate = workingDate;
//        this.productiveHours = productiveHours;
//        this.inType = inType;
//        this.status = status;
//    }
}

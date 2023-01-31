package com.quinbay.timesheet.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="timesheet")
public class Timesheet implements Serializable {
    public enum InType {
        WORK_FROM_HOME, OFFICE ;
    }


    @Id
    @SequenceGenerator(name = "my_seq", sequenceName = "my_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    Integer id;

    @Column(name = "emp_name")
    String empName;

    @Column(name = "emp_code")
    String empCode;

    @Column(name = "manager_id")
    String managerId;

    @Column(name = "working_date")
    LocalDate workingDate;

    @Column(name = "hours")
    Double hours;

    @Column(name = "in_type")
    @Enumerated(EnumType.STRING)
    InType inType;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    Approval.Status status;


    @OneToOne(mappedBy = "timesheet", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "approval_id", referencedColumnName = "id")
    private Approval approval;

    public Timesheet(String empName,String empCode,  String managerId, LocalDate workingDate, Double hours, InType inType,Approval.Status status) {
        this.empName = empName;
        this.empCode = empCode;
        this.managerId = managerId;
        this.workingDate = workingDate;
        this.hours = hours;
        this.inType = inType;
        if(approval != null) {
            this.status = approval.getStatus();
        }else{
            this.status = status;
        }
    }

    public Timesheet(String empCode, String empName, String managerId, LocalDate workingDate, Double hours, InType inType, Approval.Period period, Approval.Status status) {
        this.empCode = empCode;
        this.empName = empName;
        this.managerId = managerId;
        this.workingDate = workingDate;
        this.hours = hours;
        this.inType = inType;
        this.approval.period = period;
        if(approval != null) {
            this.status = approval.getStatus();
        }else{
            this.status = status;
        }
    }





}
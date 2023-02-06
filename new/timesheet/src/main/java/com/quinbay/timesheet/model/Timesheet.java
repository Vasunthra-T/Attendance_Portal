package com.quinbay.timesheet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="timesheet")
public class Timesheet implements Serializable {

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
    Date workingDate;

    @Column(name = "wfh_hours")
    Double wfhHours;

    @Column(name = "office_hours")
    Double officeHours;

    @Column(name = "productive_hours")
    Double productiveHours;

    @Column(name = "actual_simulator_hours")
    Double actualHours;

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "approval_id", referencedColumnName = "id")
    @JsonIgnore
    private Approval approval;

    public Timesheet(String empName,String empCode,  String managerId, Date workingDate,Double wfhHours,Double officeHours,Double productiveHours, Double actualHours) {
        this.empName = empName;
        this.empCode = empCode;
        this.managerId = managerId;
        this.workingDate = workingDate;
        this.wfhHours = wfhHours;
        this.officeHours = officeHours;
        this.productiveHours = productiveHours;
        this.actualHours = actualHours;
    }

//    public Timesheet( String empName,String empCode, String managerId, Date workingDate, Double hours, Approval.InType inType, Approval.Period period, Approval.Status status) {
//        this.empName = empName;
//        this.empCode = empCode;
//        this.managerId = managerId;
//        this.workingDate = workingDate;
//        this.productiveHours = hours;
//        this.approval.inType = inType;
//        this.approval.period = period;
//        this.approval.status = status;
//
//        }
    }


package com.quinbay.timesheet.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="approval")
public class Approval implements Serializable {
    public enum InType {
        WORK_FROM_HOME, OFFICE, BOTH ;
    }
    public enum Period {
        FIRST_HALF, SECOND_HALF ,NULL;
    }
    public enum Status{
        APPROVED, WAITING_FOR_APPROVAL;
    }

    @Id
    @SequenceGenerator(name = "myseq", sequenceName = "myseq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "myseq")
    Integer id;

    @Column(name = "emp_code")
    String empCode;


    @Column(name = "day_count")
    Double dayCount;

    @Column(name = "in_type")
    @Enumerated(EnumType.STRING)
    InType inType;


    @Column(name = "leave_period")
    @Enumerated(EnumType.STRING)
    Period period;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    Status status;

    @OneToOne(mappedBy = "approval")
    private Timesheet timesheet;

    public Approval(String empCode, Double dayCount,InType inType, Period period, Status status,Timesheet timesheet) {
        this.empCode = empCode;
        this.dayCount = dayCount;
        this.inType = inType;
        this.period = period;
        this.status = status;
        this.timesheet = timesheet;
    }



}

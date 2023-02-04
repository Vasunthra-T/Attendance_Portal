package com.quinbay.email.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

public class Timesheet {
    public enum InType {
        WORK_FROM_HOME, OFFICE, BOTH ;
    }
    Integer id;

    String empName;

    String empCode;

    String managerId;

    LocalDate workingDate;

    Double wfhHours;

    Double officeHours;

    Double productiveHours;

    Double actualHours;

    InType inType;

    Approval.Status status;

    @OneToOne(mappedBy = "timesheet", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "approval_id", referencedColumnName = "id")
    @JsonIgnore
    private Approval approval;
}

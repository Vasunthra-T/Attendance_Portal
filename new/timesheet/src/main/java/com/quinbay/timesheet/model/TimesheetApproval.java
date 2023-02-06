package com.quinbay.timesheet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimesheetApproval implements Serializable {
    String empCode;

    String empName;

    Date workingDate;

    Double productiveHours;

    Approval.InType inType;

    Approval.Status status;

}

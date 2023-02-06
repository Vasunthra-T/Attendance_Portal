package com.quinbay.timesheet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimesheetApproval implements Serializable {
    String empCode;
    String empName;
    LocalDate workingDate;
    Double productiveHours;
    Approval.InType inType;
    Approval.Status status;

}

package com.quinbay.timesheet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalResponse {
    String empCode;
    String empName;
    Date workingDate;
    Double hours;
    Approval.InType inType;
    Approval.Period leave_period;
    Approval.Status status;

}

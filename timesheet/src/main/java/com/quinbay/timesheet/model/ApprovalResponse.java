package com.quinbay.timesheet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalResponse {
    String empCode;
    String empName;
    LocalDate workingDate;
    Double hours;
    Approval.InType inType;
    Approval.Period leave_period;
    Approval.Status status;

}

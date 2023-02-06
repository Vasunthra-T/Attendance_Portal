package com.quinbay.timesheet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimesheetResponse {
    String empCode;
    String empName;
    LocalDate workingDate;
    Double hours;
    Approval.InType inType;
    Approval.Status status;
}

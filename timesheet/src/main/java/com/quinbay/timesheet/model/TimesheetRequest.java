package com.quinbay.timesheet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimesheetRequest implements Serializable {
    String empCode;
    String managerId;
    LocalDate workingDate;
    Double wfhHours;
    Double officeHours;
    Double productiveHours;
    Approval.InType inType;
    Double dayCount;
    Approval.Period period;

}

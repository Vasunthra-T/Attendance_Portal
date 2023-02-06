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
public class TimesheetRequest implements Serializable {
    String empCode;
    String managerId;
    Date workingDate;
    Double wfhHours;
    Double officeHours;
    Double productiveHours;
    Approval.InType inType;
    Double dayCount;
    Approval.Period period;

}

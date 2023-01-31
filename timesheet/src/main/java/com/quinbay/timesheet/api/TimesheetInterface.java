package com.quinbay.timesheet.api;

import com.quinbay.timesheet.model.BasicReturn;
import com.quinbay.timesheet.model.TimesheetPojo;
import com.quinbay.timesheet.model.TimesheetShowPojo;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface TimesheetInterface {
    ResponseEntity<String> addTimesheet(TimesheetPojo timesheetPojo);

    ResponseEntity<Double> calculateHours(String empCode, LocalDate workingDate);

    List<TimesheetShowPojo> generateSummary(String empCode, LocalDate fromDate, LocalDate toDate);

   // ResponseEntity<String> getEmployeeDetails(String empCode);

     ResponseEntity<Object> getEmployeeDetails(String empCode);
}

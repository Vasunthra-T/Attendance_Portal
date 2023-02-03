package com.quinbay.timesheet.api;

import com.quinbay.timesheet.model.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface TimesheetInterface {
    String addTimesheet(TimesheetRequest timesheetRequest);

    Double calculateHours(String empCode, LocalDate workingDate);

    List<TimesheetResponse> generateSummary(String empCode, LocalDate fromDate, LocalDate toDate);

     Object getEmployeeDetails(String empCode);

   //  List<Timesheet> displayByPages(int pageNo,int pageSize);

     List<Timesheet> displayAll();

    List<Simulator> getSimulatorDetails(String empCode, LocalDate workingDate);

    List<TimesheetResponse> fetchFromTimesheet(String empCode, LocalDate fromDate, LocalDate toDate);

    String approveEmployee(String empCode,LocalDate workingDate, Approval.Status status);

    List<TimesheetResponse> generateSummaryByPaging(String empCode, LocalDate fromDate, LocalDate toDate,int pageNo, int pageSize);

}

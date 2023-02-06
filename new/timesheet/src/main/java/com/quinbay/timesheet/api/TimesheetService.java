package com.quinbay.timesheet.api;

import com.quinbay.timesheet.model.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TimesheetService {
    String addTimesheet(TimesheetRequest timesheetRequest);

    Double calculateHours(String empCode, Date workingDate);

    List<TimesheetResponse> generateSummary(String empCode, Date fromDate, Date toDate);

     Object getEmployeeDetails(String empCode);

   //  List<Timesheet> displayByPages(int pageNo,int pageSize);

     List<Timesheet> displayAll();

    List<Simulator> getSimulatorDetails(String empCode, Date workingDate);

    List<TimesheetResponse> fetchFromTimesheet(String empCode, Date fromDate, Date toDate);

    String approveEmployee(String empCode,Date workingDate, Approval.Status status);

    List<TimesheetResponse> generateSummaryByPaging(String empCode, Date fromDate, Date toDate,int pageNo, int pageSize);

}

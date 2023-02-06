package com.quinbay.email.api;

import com.quinbay.email.model.TimesheetApproval;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface SummaryService {
    String generatePdf(List<TimesheetApproval> t,String empCode, String email);

    String splitByEmployee(Date fromDate);

    List<TimesheetApproval> fetchFromTimesheet(String empCode, Date fromDate, Date toDate);
}

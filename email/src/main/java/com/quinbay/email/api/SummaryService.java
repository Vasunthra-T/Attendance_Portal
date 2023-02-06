package com.quinbay.email.api;

import com.quinbay.email.model.TimesheetApproval;

import java.time.LocalDate;
import java.util.List;

public interface SummaryService {
    String generatePdf(List<TimesheetApproval> t,String empCode, String email);

    String splitByEmployee(LocalDate fromDate);

    List<TimesheetApproval> fetchFromTimesheet(String empCode, LocalDate fromDate, LocalDate toDate);
}

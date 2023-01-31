package com.quinbay.timesheet.api;

import com.quinbay.timesheet.model.ApprovalPojo;
import com.quinbay.timesheet.model.TimesheetShowPojo;

import java.io.FileNotFoundException;

public interface SummaryInterface {
    String generatePdf(TimesheetShowPojo myList) throws FileNotFoundException;

}

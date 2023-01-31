package com.quinbay.timesheet.api;

import com.quinbay.timesheet.model.Approval;
import com.quinbay.timesheet.model.ApprovalPojo;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface ApprovalInterface {
    List<ApprovalPojo> showApproval(String empCode, LocalDate fromDate, LocalDate toDate, Approval.Status status);
}

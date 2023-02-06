package com.quinbay.timesheet.api;

import com.quinbay.timesheet.model.Approval;
import com.quinbay.timesheet.model.ApprovalResponse;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ApprovalService {
    List<ApprovalResponse> showApproval(String empCode, Date fromDate, Date toDate, Approval.Status status);

    ResponseEntity<Object> getSubordinates(String empCode);

}

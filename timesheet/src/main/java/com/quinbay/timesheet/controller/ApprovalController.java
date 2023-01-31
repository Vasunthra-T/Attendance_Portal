package com.quinbay.timesheet.controller;

import com.quinbay.timesheet.api.ApprovalInterface;
import com.quinbay.timesheet.model.Approval;
import com.quinbay.timesheet.model.ApprovalPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/approval")
public class ApprovalController {

    @Autowired
    ApprovalInterface approvalInterface;

    @GetMapping("showApprovals/{empCode}/{fromDate}/{toDate}/{statusString}")
    public List<ApprovalPojo> showApproval(@PathVariable String empCode, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
                                           @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate, @PathVariable  String statusString){
        Approval.Status status = Approval.Status.valueOf(statusString);

        System.out.println(status);
        return approvalInterface.showApproval(empCode,fromDate,toDate,status);
    }
}

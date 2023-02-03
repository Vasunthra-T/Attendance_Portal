package com.quinbay.timesheet.controller;

import com.quinbay.timesheet.api.ApprovalInterface;
import com.quinbay.timesheet.model.Approval;
import com.quinbay.timesheet.model.ApprovalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/approval")
public class ApprovalController {

    @Autowired
    ApprovalInterface approvalInterface;

    @GetMapping("showApprovals")
    public List<ApprovalResponse> showApproval(@RequestParam String empCode, @RequestParam(value = "fromDate",required =false ,defaultValue = "2016-01-01") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
                                               @RequestParam(value = "toDate",required =false ,defaultValue = "#{T(java.time.LocalDate).now()}") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate, @RequestParam(value = "statusString",required =false ,defaultValue = "WAITING_FOR_APPROVAL")  String statusString){
        Approval.Status status = Approval.Status.valueOf(statusString);

        return approvalInterface.showApproval(empCode,fromDate,toDate,status);
    }

    @GetMapping("fetchSubordinates/{empCode}")
    public ResponseEntity<Object> getSubordinates(@PathVariable String empCode){
        return approvalInterface.getSubordinates(empCode);
    }


}

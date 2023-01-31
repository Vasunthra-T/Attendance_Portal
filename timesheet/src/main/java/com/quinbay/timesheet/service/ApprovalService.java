package com.quinbay.timesheet.service;

import com.quinbay.timesheet.api.ApprovalInterface;
import com.quinbay.timesheet.model.Approval;
import com.quinbay.timesheet.model.ApprovalPojo;
import com.quinbay.timesheet.model.Timesheet;
import com.quinbay.timesheet.repository.ApprovalRepository;
import com.quinbay.timesheet.repository.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApprovalService implements ApprovalInterface {
    @Autowired
    ApprovalRepository approvalRepository;

    @Autowired
    TimesheetRepository timesheetRepository;

    @Override
    public List<ApprovalPojo> showApproval(String empCode, LocalDate fromDate, LocalDate toDate, Approval.Status status) {
        System.out.println(status);
        List<Timesheet> subordinates = timesheetRepository.findByManagerIdAndStatusAndWorkingDateBetween(empCode,status, fromDate, toDate);
        System.out.println(subordinates.get(0).getEmpCode());

        System.out.println(subordinates.get(0).getEmpName());
        List<ApprovalPojo> myList = new ArrayList<>();
        for (Timesheet time : subordinates) {
            ApprovalPojo correctTimesheet = new ApprovalPojo(time.getEmpCode(), time.getEmpName(), time.getWorkingDate(), time.getHours(), time.getInType(), time.getApproval().getPeriod(), time.getStatus());
            myList.add(correctTimesheet);

        }
        return myList;
    }





}

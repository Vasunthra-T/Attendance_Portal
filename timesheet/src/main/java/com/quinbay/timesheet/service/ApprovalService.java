package com.quinbay.timesheet.service;

import com.quinbay.timesheet.api.ApprovalInterface;
import com.quinbay.timesheet.model.*;
import com.quinbay.timesheet.repository.ApprovalRepository;
import com.quinbay.timesheet.repository.EmployeeRepository;
import com.quinbay.timesheet.repository.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ApprovalService implements ApprovalInterface {
    @Autowired
    ApprovalRepository approvalRepository;

    @Autowired
    TimesheetRepository timesheetRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<ApprovalResponse> showApproval(String empCode, LocalDate fromDate, LocalDate toDate, Approval.Status status) {
        List<Timesheet> subordinates = timesheetRepository.findByEmpCodeAndStatusAndWorkingDateBetween(empCode,status, fromDate, toDate);

        List<ApprovalResponse> myList = new ArrayList<>();
        for (Timesheet time : subordinates) {
            ApprovalResponse correctTimesheet = new ApprovalResponse(time.getEmpCode(), time.getEmpName(), time.getWorkingDate(), time.getProductiveHours(), time.getInType(), time.getApproval().getPeriod(), time.getApproval().getStatus());
            myList.add(correctTimesheet);
        }
        return myList;
    }

    @Override
    public ResponseEntity<Object> getSubordinates(String empCode) {
        Optional<Employee> employees = employeeRepository.findByEmpCode(empCode);
        String role = employees.get().getRole();

        if(employees.isPresent()) {
            Set<BasicDetails> mySet = new HashSet<>();
            if (role.equals("Manager")) {
                List<Timesheet> subordinates = timesheetRepository.findByManagerId(empCode);

                for (Timesheet employee : subordinates) {
                    BasicDetails basicDetails = new BasicDetails();
                    basicDetails.setEmpCode(employee.getEmpCode());
                    basicDetails.setEmpName(employee.getEmpName());
                    mySet.add(basicDetails);
                }
                return new ResponseEntity<Object>(mySet,HttpStatus.OK);
            } else {

                return new ResponseEntity<Object>("Sign up as manager", HttpStatus.NOT_FOUND);
            }
        }
        else {
            return new ResponseEntity<Object>("Employee not found",HttpStatus.NOT_FOUND);
        }
    }
}

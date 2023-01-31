package com.quinbay.timesheet.controller;

import com.quinbay.timesheet.api.TimesheetInterface;
import com.quinbay.timesheet.model.BasicReturn;
import com.quinbay.timesheet.model.TimesheetPojo;
import com.quinbay.timesheet.model.TimesheetShowPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/time")
public class TimesheetController {

    @Autowired
    TimesheetInterface timesheetInterface;

    @PostMapping("addTimesheet")
    public ResponseEntity<String> addTimesheet(@RequestBody TimesheetPojo timesheetPojo){
        return timesheetInterface.addTimesheet(timesheetPojo);
    }

    @GetMapping("calculateHours")
    public ResponseEntity<Double> calculateHours(@RequestParam String empCode, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate workingDate)
    {
        return timesheetInterface.calculateHours(empCode,workingDate);
    }

    @GetMapping("generateSummary/{empCode}/{fromDate}/{toDate}")
    public List<TimesheetShowPojo> generateSummary(@PathVariable String empCode, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
                                         @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate){
        return timesheetInterface.generateSummary(empCode,fromDate,toDate);
    }

//    @GetMapping("fetchEmpSummary/{empCode}")
//    public ResponseEntity<String> getEmployeeDetails(@PathVariable String empCode){
//        return timesheetInterface.getEmployeeDetails(empCode);
//    }

    @GetMapping("fetchEmpSummary/{empCode}")
    public ResponseEntity<Object> getEmployeeDetails(@PathVariable String empCode){
        return timesheetInterface.getEmployeeDetails(empCode);
    }



}

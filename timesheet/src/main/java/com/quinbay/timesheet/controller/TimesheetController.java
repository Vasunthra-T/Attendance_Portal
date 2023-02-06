package com.quinbay.timesheet.controller;

import com.quinbay.timesheet.api.TimesheetService;
import com.quinbay.timesheet.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/time")
public class TimesheetController {

    @Autowired
    TimesheetService timesheetService;

    @PostMapping("addTimesheet")
    public ResponseEntity<String> addTimesheet(@RequestBody TimesheetRequest timesheetRequest){
        String result = timesheetService.addTimesheet(timesheetRequest);
        HttpStatus status = (result.equals("Entry is added already in the timesheet")) ? HttpStatus.CONFLICT : HttpStatus.OK;
        return new ResponseEntity<>(result, status);
    }


    @GetMapping("calculateHours")
    public ResponseEntity<Double> calculateHours(@RequestParam String empCode, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate workingDate)
    {
        Double result  = timesheetService.calculateHours(empCode,workingDate);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("generateSummary")
    public List<TimesheetResponse> generateSummary(@RequestParam String empCode, @RequestParam(value = "fromDate",required =false ,defaultValue = "#{T(java.time.LocalDate).now()}") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
                                                   @RequestParam(value = "toDate",required =false ,defaultValue = "#{T(java.time.LocalDate).now()}") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate){
        return timesheetService.generateSummary(empCode,fromDate,toDate);
    }

    @GetMapping("fetchEmpSummary/{empCode}")
    public ResponseEntity<Object> getEmployeeDetails(@PathVariable String empCode){
        Object result = timesheetService.getEmployeeDetails(empCode);
        HttpStatus status = (result.equals("Employee not found")) ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(result, status);
    }

    @GetMapping("displayAll")
    public List<Timesheet> displayAll(){
        return  timesheetService.displayAll();
    }

    @GetMapping("getSimulatorDetails/{empCode}/{workingDate}")
    public List<Simulator> getSimulatorDetails(@PathVariable String empCode, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate workingDate){
        return timesheetService.getSimulatorDetails(empCode,workingDate);
    }

    @PutMapping("approveEmployee")
    public ResponseEntity<String> approveEmployee(@RequestParam String empCode, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate workingDate,
                                                  @RequestParam  String statusString       ){
        statusString.toUpperCase();
        Approval.Status status = Approval.Status.valueOf(statusString);
        String result = timesheetService.approveEmployee(empCode,workingDate,status) ;
        HttpStatus statusCode = (result.equals("Employee doesn't exist")) ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(result, statusCode);

    }

    @GetMapping("generateSummaryByPaging")
    public List<TimesheetResponse> generateSummaryByPaging(@RequestParam String empCode, @RequestParam(value = "fromDate",required =false ,defaultValue = "2016-01-01") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
                                                   @RequestParam(value = "toDate",required =false ,defaultValue = "#{T(java.time.LocalDate).now()}") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
                                                           @RequestParam int pageNo,int pageSize){
        return timesheetService.generateSummaryByPaging(empCode,fromDate,toDate,pageNo,pageSize);
    }




//    @GetMapping("pagination/{pageNo}/{pageSize}")
//    public List<Timesheet> displayByPages(@PathVariable int pageNo,@PathVariable int pageSize)
//    {
//        return timesheetService.displayByPages(pageNo,pageSize);
//    }





}

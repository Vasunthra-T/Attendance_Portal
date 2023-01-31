package com.quinbay.timesheet.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quinbay.timesheet.api.SummaryInterface;
import com.quinbay.timesheet.api.TimesheetInterface;
import com.quinbay.timesheet.model.*;
import com.quinbay.timesheet.repository.ApprovalRepository;
import com.quinbay.timesheet.repository.EmployeeRepository;
import com.quinbay.timesheet.repository.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TimesheetService implements TimesheetInterface {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    TimesheetRepository timesheetRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ApprovalRepository approvalRepository;

    @Autowired
    SummaryInterface summaryInterface;


    @Override
    public ResponseEntity<String> addTimesheet(TimesheetPojo timesheetPojo) {
        Optional<Employee> opt = employeeRepository.findByEmpCode(timesheetPojo.getEmpCode());
        Approval.Status status = Approval.Status.WAITING_FOR_APPROVAL;

        if (timesheetPojo.getHours() <= 6 || (timesheetPojo.getInType() == Timesheet.InType.WORK_FROM_HOME)) {
            status = Approval.Status.WAITING_FOR_APPROVAL;
        } else {
            status = Approval.Status.APPROVED;
        }

        Timesheet timesheet = new Timesheet(opt.get().getEmpName(), timesheetPojo.getEmpCode(), timesheetPojo.getManagerId(), timesheetPojo.getWorkingDate(), timesheetPojo.getHours(), timesheetPojo.getInType(), status);
        timesheetRepository.save(timesheet); //save timesheet first

        Approval approval = new Approval(timesheetPojo.getEmpCode(), timesheetPojo.getManagerId(), timesheetPojo.getDayCount(), timesheetPojo.getPeriod(), status, timesheet);
        timesheet.setApproval(approval);
        approvalRepository.save(approval);

        return new ResponseEntity<String>("Timesheet added successfully", HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Double> calculateHours(String empCode, LocalDate workingDate) {

        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/simulate/calculateHours")
                .queryParam("empCode", empCode)
                .queryParam("workingDate", workingDate)
                .toUriString();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        Double totalHours = restTemplate().getForObject(url, Double.class);
        System.out.println(totalHours);
        return new ResponseEntity<Double>(totalHours, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getEmployeeDetails(String empCode) {
        Optional<Employee> employees = employeeRepository.findByEmpCode(empCode);
        String role = employees.get().getRole();

        return new ResponseEntity<Object>("",HttpStatus.OK);

    }

//    @Override
//    public ResponseEntity<String> getEmployeeDetails(String empCode) {
//        Optional<Employee> employees = employeeRepository.findByEmpCode(empCode);
//        String role = employees.get().getRole();
//
//        List<List<String>> myList = new ArrayList<>();
//
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString="";
//
//        if(role.equals("Manager")){
//            List<Timesheet> subordinates = timesheetRepository.findByManagerId(empCode);
//            try {
//
//                for (Timesheet employee : subordinates) {
//                    List<String> list1 = new ArrayList<>();
//
//                    list1.add(employee.getEmpCode());
//                    list1.add(employee.getEmpName());
//                    myList.add(list1);
//                }
//                List<String> list2 = new ArrayList<>();
//                list2.add(employees.get().getEmpCode());
//                list2.add(employees.get().getEmpName());
//
//                myList.add(list2);
//                jsonString = mapper.writeValueAsString(myList);
//
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//
//        }
//        else {
//            try {
//                List<String> list1 = new ArrayList<>();
//                list1.add(employees.get().getEmpCode());
//                list1.add(employees.get().getEmpName());
//
//                myList.add(list1);
//                jsonString = mapper.writeValueAsString(myList);
//
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//        }
//            return new ResponseEntity<String>(jsonString, HttpStatus.OK);
//        }




   @Override
    public List<TimesheetShowPojo> generateSummary(String empCode, LocalDate fromDate, LocalDate toDate) {
        System.out.println(fromDate);
        System.out.println(toDate);

      //  Optional<Employee> opt = employeeRepository.findByEmpCode(empCode);
        List<TimesheetShowPojo> myList = new ArrayList<>();
        List<Timesheet> filterTimesheet = timesheetRepository.findByEmpCodeAndWorkingDateBetween(empCode, fromDate, toDate);
        System.out.println(filterTimesheet.get(0).getEmpName());
        for (Timesheet time : filterTimesheet) {
            TimesheetShowPojo correctTimesheet = new TimesheetShowPojo(empCode, time.getEmpName(), time.getWorkingDate(), time.getHours(), time.getInType(), time.getStatus());
            myList.add(correctTimesheet);
        }
       summaryInterface.generatePdf(myList);


       return myList;
    }

//        String role = opt.get().getRole();
//        String emp = opt.get().getEmpCode();
//        System.out.println(emp);
//        if (role.equals("Manager")) {
//            if (opt.get().getEmpCode().equals(empCode)) {
//                List<Timesheet> filterTimesheet = timesheetRepository.findByEmpCodeAndWorkingDateBetween(empCode, fromDate, toDate);
//                System.out.println(filterTimesheet.get(0).getEmpName());
//                for (Timesheet time : filterTimesheet) {
//                    TimesheetShowPojo correctTimesheet = new TimesheetShowPojo(empCode, time.getEmpName(), time.getWorkingDate(), time.getHours(), time.getInType(), time.getStatus());
//                    myList.add(correctTimesheet);
//                }
//                return myList;
//
//            } else {
//
//                System.out.println(role);
//
//                List<Timesheet> subordinates = timesheetRepository.findByManagerIdAndWorkingDateBetween(empCode, fromDate, toDate);
//                System.out.println(subordinates.get(0).getEmpCode());
//
//                System.out.println(subordinates.get(0).getEmpName());
//                for (Timesheet time : subordinates) {
//                    TimesheetShowPojo correctTimesheet = new TimesheetShowPojo(time.getEmpCode(), time.getEmpName(), time.getWorkingDate(), time.getHours(), time.getInType(), time.getStatus());
//                    myList.add(correctTimesheet);
//                }
//                return myList;
//            }
//        }
//
//         else {
//
//            List<Timesheet> filterTimesheet = timesheetRepository.findByEmpCodeAndWorkingDateBetween(empCode, fromDate, toDate);
//            System.out.println(filterTimesheet.get(0).getEmpName());
//            for (Timesheet time : filterTimesheet) {
//                TimesheetShowPojo correctTimesheet = new TimesheetShowPojo(empCode, time.getEmpName(), time.getWorkingDate(), time.getHours(), time.getInType(), time.getStatus());
//                myList.add(correctTimesheet);
//            }
//            return myList;
//
//        }
//
//
//    }
}

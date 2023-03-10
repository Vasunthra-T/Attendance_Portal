package com.quinbay.timesheet.service;

import com.quinbay.timesheet.api.TimesheetService;
import com.quinbay.timesheet.kafka.KafkaPublishingService;
import com.quinbay.timesheet.model.*;
import com.quinbay.timesheet.repository.ApprovalRepository;
import com.quinbay.timesheet.repository.EmployeeRepository;
import com.quinbay.timesheet.repository.TimesheetRepository;
import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
public class TimesheetServiceImpl implements TimesheetService {
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
    KafkaPublishingService kafkaPublishingService;


    @Override
    public String addTimesheet(TimesheetRequest timesheetRequest) {
        Optional<Employee> opt = employeeRepository.findByEmpCode(timesheetRequest.getEmpCode());
        Optional<Timesheet> time = timesheetRepository.findByEmpCodeAndWorkingDate(timesheetRequest.getEmpCode(), timesheetRequest.getWorkingDate());
        Approval.Status status = Approval.Status.WAITING_FOR_APPROVAL;

        System.out.println(timesheetRequest.getWorkingDate().getClass());

        if (time.isPresent()) {
            time.get().getEmpCode();
            return "Entry is added already in the timesheet";
        } else {
            if (opt.get().getManagerId().equals("")) {
                status = Approval.Status.APPROVED;
            } else if (timesheetRequest.getProductiveHours() <= 6 || (timesheetRequest.getInType() == Approval.InType.WORK_FROM_HOME) || (timesheetRequest.getInType() == Approval.InType.BOTH)) {
                status = Approval.Status.WAITING_FOR_APPROVAL;
            } else {
                status = Approval.Status.APPROVED;
            }
            Double actualHours = calculateHours(opt.get().getEmpCode(), timesheetRequest.getWorkingDate());

            Timesheet timesheet = new Timesheet(opt.get().getEmpName(), timesheetRequest.getEmpCode(), opt.get().getManagerId(), timesheetRequest.getWorkingDate(), timesheetRequest.getWfhHours(), timesheetRequest.getOfficeHours(), timesheetRequest.getProductiveHours(), actualHours);
            timesheetRepository.save(timesheet); //save timesheet first

            Approval approval = new Approval(timesheetRequest.getEmpCode(), timesheetRequest.getDayCount(),timesheetRequest.getInType(), timesheetRequest.getPeriod(), status, timesheet);
            timesheet.setApproval(approval);
            approvalRepository.save(approval);

            TimesheetApproval timesheetApproval = new TimesheetApproval(timesheetRequest.getEmpCode(), opt.get().getEmpName(), timesheetRequest.getWorkingDate(), timesheetRequest.getProductiveHours(), timesheetRequest.getInType(), status);
            kafkaPublishingService.sendTimesheetApprovalDetails(timesheetApproval);
            return "Timesheet added successfully";
        }
    }

    @Override
    public Double calculateHours(String empCode, Date workingDate) {

        try {
            String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8091/simulate/calculateHours")
                    .queryParam("empCode", empCode)
                    .queryParam("workingDate", workingDate)
                    .toUriString();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<String>(headers);

            System.out.println(empCode);

            Double totalHours = restTemplate().getForObject(url, Double.class);
            System.out.println(totalHours);
            return totalHours;
        }
        catch(Exception e){
            System.out.println("\n------------"+e+"---------------->");
        }
   return null;
    }

    @Override
    public Object getEmployeeDetails(String empCode) {
        Optional<Employee> employees = employeeRepository.findByEmpCode(empCode);
        String role = employees.get().getRole();

        if (employees.isPresent()) {
            Set<BasicDetails> mySet = new HashSet<>();
            if (role.equals("Manager")) {
                List<Timesheet> subordinates = timesheetRepository.findByManagerId(empCode);
                List<Timesheet> manager = timesheetRepository.findByEmpCode(empCode);
                for (Timesheet emp : manager) {
                    BasicDetails basicDetails = new BasicDetails();
                    basicDetails.setEmpCode(empCode);
                    basicDetails.setEmpName(emp.getEmpName());
                    mySet.add(basicDetails);
                }
                for (Timesheet employee : subordinates) {
                    BasicDetails basicDetails = new BasicDetails();
                    basicDetails.setEmpCode(employee.getEmpCode());
                    basicDetails.setEmpName(employee.getEmpName());
                    mySet.add(basicDetails);
                }
            } else {
                BasicDetails basicDetails = new BasicDetails();
                basicDetails.setEmpCode(employees.get().getEmpCode());
                basicDetails.setEmpName(employees.get().getEmpName());
                mySet.add(basicDetails);
            }
            return mySet;
        } else {
            return "Employee not found";
        }
    }

    @Override
    public List<TimesheetResponse> generateSummary(String empCode, Date fromDate, Date toDate) {
        if (fromDate.equals(toDate)) {
           // List<Date> weekDates = Arrays.asList(DayOfWeek.values()).stream().map(toDate::with).collect(toList());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fromDate);

            // Find the first day of the week (Sunday)
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            Date firstDayOfWeek = calendar.getTime();

            // Find the last day of the week (Saturday)
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
            Date lastDayOfWeek = calendar.getTime();

            // Create a list to store the dates of the week
            List<Date> weekDates = new ArrayList<>();

            // Add each date from the first day of the week to the last day of the week to the list
            calendar.setTime(firstDayOfWeek);
            while (calendar.getTime().before(lastDayOfWeek)) {
                weekDates.add(calendar.getTime());
                calendar.add(Calendar.DATE, 1);
            }
            weekDates.add(lastDayOfWeek);

            for (Date date : weekDates) {
                if (date.getDay() == 1) {
                    fromDate = date;
                    return fetchFromTimesheet(empCode, fromDate, toDate);
                }
            }
        } else {
            return fetchFromTimesheet(empCode, fromDate, toDate);
        }
        return fetchFromTimesheet(empCode, fromDate, toDate);
    }

    @Override
    public List<TimesheetResponse> fetchFromTimesheet(String empCode, Date fromDate, Date toDate) {
        List<TimesheetResponse> myList = new ArrayList<>();

        try {
            List<Timesheet> filterTimesheet = timesheetRepository.findByEmpCodeAndWorkingDateBetweenOrderByWorkingDateDesc(empCode, fromDate, toDate);

            for (Timesheet time : filterTimesheet) {
                TimesheetResponse correctTimesheet = new TimesheetResponse(empCode, time.getEmpName(), time.getWorkingDate(), time.getProductiveHours(), time.getApproval().getInType(), time.getApproval().getStatus());
                myList.add(correctTimesheet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myList;
    }

    @Override
    public List<Simulator> getSimulatorDetails(String empCode, Date workingDate) {
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/simulate/getDetails")
                .queryParam("empCode", empCode)
                .queryParam("workingDate", workingDate)
                .toUriString();
        ParameterizedTypeReference<List<Simulator>> typeReference = new ParameterizedTypeReference<List<Simulator>>() {
        };
        ResponseEntity<List<Simulator>> response = restTemplate().exchange(url, HttpMethod.GET, null, typeReference);
        List<Simulator> simulators = response.getBody();
        return simulators;
    }

    @Override
    public List<Timesheet> displayAll() {
        List<Timesheet> timesheetList = timesheetRepository.findAll();
        return timesheetList;
    }

    @Override
    public String approveEmployee(String empCode, Date workingDate, Approval.Status status) {
        Optional<Timesheet> opt = timesheetRepository.findByEmpCodeAndWorkingDate(empCode, workingDate);
        System.out.println(opt.get().getEmpCode());
        System.out.println(opt.get().getWorkingDate());
        if (opt.isPresent()) {
            if (opt.get().getApproval().getStatus().equals(Approval.Status.WAITING_FOR_APPROVAL)) {
                opt.get().getApproval().setStatus(status);
                //opt.get().setStatus(status);
                timesheetRepository.save(opt.get());
                return " Timesheet entry approved successfully";
            }
        }
        return "Employee doesn't exist";
    }


    @Override
    public List<TimesheetResponse> generateSummaryByPaging(String empCode, Date fromDate, Date toDate, int pageNo, int pageSize) {
        List<TimesheetResponse> myList = new ArrayList<>();

        try {
            Pageable paging = PageRequest.of(pageNo, pageSize);
            List<Timesheet> page = timesheetRepository.findByEmpCodeAndWorkingDateBetween(empCode, fromDate, toDate, paging);

            for (Timesheet time : page) {
                TimesheetResponse correctTimesheet = new TimesheetResponse(empCode, time.getEmpName(), time.getWorkingDate(), time.getProductiveHours(), time.getApproval().getInType(), time.getApproval().getStatus());
                myList.add(correctTimesheet);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return myList;

    }
}

//    @Override
//    public List<Timesheet> displayByPages(int pageNo, int pageSize){
//        Pageable paging  = PageRequest.of(pageNo,pageSize);
//        Page<Timesheet> page =timesheetRepository.findAll(paging);
//        return page.toList();
//    }



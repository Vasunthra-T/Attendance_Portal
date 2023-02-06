package com.quinbay.simulator.service;

import com.quinbay.simulator.api.SimulatorService;
import com.quinbay.simulator.model.*;
import com.quinbay.simulator.repository.EmployeeRepository;
import com.quinbay.simulator.repository.HoursRepository;
import com.quinbay.simulator.repository.SimulatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@Service
public class SimulatorServiceImpl implements SimulatorService {
    @Autowired
    SimulatorRepository simulatorRepository;

    @Autowired
    HoursRepository hoursRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    Hours entry =null;

    @Override
    public String addSimulator(SimulatorRequest simulatorRequest) throws ParseException{
        int flag= 0;
        Optional<Employee> emp = employeeRepository.findByEmpCode(simulatorRequest.getEmpCode());
        System.out.println(emp.get().getEmpCode());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date inTime = dateFormat.parse(simulatorRequest.getInTime());
        Date outTime = dateFormat.parse(simulatorRequest.getOutTime());

        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(simulatorRequest.getInTime());
        String formattedDate = outputFormat.format(date);
        Date finalDate = outputFormat.parse(formattedDate);
        System.out.println(finalDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date findDate = calendar.getTime();
        System.out.println(findDate);

        List<Simulator> simulator = simulatorRepository.findByEmpCodeAndWorkingDate(simulatorRequest.getEmpCode(),findDate);

        if(emp.isPresent()) {
            if (simulator.isEmpty()) {
                return createEntry(simulatorRequest,finalDate);
            } else  {
                for (Simulator s : simulator) {
                    double diff = inTime.compareTo(outTime);
                    if (diff < 0.0) {
                        flag = 1;
                    }
                }
            }
            if (flag == 1)
                return "The swipe in time is before the previous swipe out time";
             else
                return createEntry(simulatorRequest,finalDate);
        }
        else
            return "User doesn't exist";

    }

    @Override
    public String createEntry(SimulatorRequest simulatorRequest, Date finalDate) throws ParseException{
        System.out.println(simulatorRequest.getInTime());
        System.out.println(simulatorRequest.getOutTime());


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date inTime = dateFormat.parse(simulatorRequest.getInTime());
        Date outTime = dateFormat.parse(simulatorRequest.getOutTime());

        Simulator entry = new Simulator(simulatorRequest.getEmpCode(), finalDate, inTime, outTime);
        simulatorRepository.save(entry);
        return "Entry added to simulator successfully";
    }

    @Override
    public Double calculateHours(String empCode, Date workingDate)   {
        System.out.println(workingDate.getClass());
        System.out.println(workingDate+"-----------------");
        double totalHours =0.0;
        try {
        List<Simulator> simulator = simulatorRepository.findByEmpCodeAndWorkingDate(empCode,workingDate);
        for(Simulator s: simulator){
            System.out.println(s.getWorkingDate()+"++++++++++++++");
        }

        for(Simulator s: simulator) {

                if (workingDate.equals(s.getWorkingDate())) {
                    double diff = s.getOutTime().getTime() - s.getInTime().getTime();
                    double hours = diff / (60.0 * 60 * 1000);
                    totalHours += hours;
                }
            }
        }catch (Exception e){
            System.out.println("\n--------" +e +"------------");

        }
        System.out.println(totalHours);
        Hours addEntry = new Hours( empCode, workingDate, totalHours);
        hoursRepository.save(addEntry);

        return totalHours;
    }

    @Override
    public List<SimulatorResponse> getDetails(String empCode, Date workingDate){
        List<Simulator> simulatorList = simulatorRepository.findByEmpCodeAndWorkingDate(empCode,workingDate);

        List<SimulatorResponse> filterSimulatorList = new ArrayList<>();

        for(Simulator s: simulatorList){
            SimulatorResponse filterSimulator = new SimulatorResponse();
            filterSimulator.setInTime(s.getInTime());
            filterSimulator.setOutTime(s.getOutTime());
            filterSimulatorList.add(filterSimulator);
        }
        return filterSimulatorList;
    }
}

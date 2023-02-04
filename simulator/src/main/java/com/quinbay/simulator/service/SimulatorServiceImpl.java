package com.quinbay.simulator.service;

import com.quinbay.simulator.api.SimulatorInterface;
import com.quinbay.simulator.model.Employee;
import com.quinbay.simulator.model.Hours;
import com.quinbay.simulator.model.Simulator;
import com.quinbay.simulator.model.SimulatorRequest;
import com.quinbay.simulator.repository.EmployeeRepository;
import com.quinbay.simulator.repository.HoursRepository;
import com.quinbay.simulator.repository.SimulatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SimulatorServiceImpl implements SimulatorInterface {
    @Autowired
    SimulatorRepository simulatorRepository;

    @Autowired
    HoursRepository hoursRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    Hours entry =null;

    @Override
    public String addSimulator(SimulatorRequest simulatorRequest){
        int flag= 0;
        Optional<Employee> emp = employeeRepository.findByEmpCode(simulatorRequest.getEmpCode());
        System.out.println(emp.get().getEmpCode());

        LocalDate findDate = simulatorRequest.getInTime().toLocalDate();

        List<Simulator> simulator = simulatorRepository.findByEmpCodeAndWorkingDate(simulatorRequest.getEmpCode(),findDate);

        if(emp.isPresent()) {
            if (simulator.isEmpty()) {
                return createEntry(simulatorRequest);
            } else  {

                for (Simulator s : simulator) {
                    double diff = simulatorRequest.getInTime().compareTo(s.getOutTime());

                    if (diff < 0.0) {
                        flag = 1;
                    }
                }
            }
            if (flag == 1) {
                return "The swipe in time is before the previous swipe out time";

            } else {
                return createEntry(simulatorRequest);
            }
        }
        else {
            return "User doesn't exist";
        }
    }

    @Override
    public String createEntry(SimulatorRequest simulatorRequest){
        Simulator entry = new Simulator(simulatorRequest.getEmpCode(), simulatorRequest.getInTime().toLocalDate(), simulatorRequest.getInTime(), simulatorRequest.getOutTime());
        simulatorRepository.save(entry);
        return "Entry added to simulator successfully";
    }

    @Override
    public Double calculateHours(String empCode, LocalDate workingDate)   {
        double totalHours =0.0;

        List<Simulator> simulator = simulatorRepository.findByEmpCodeAndWorkingDate(empCode,workingDate);
        for(Simulator s: simulator) {
            if (workingDate.equals(s.getWorkingDate())) {

                Duration duration = Duration.between(s.getInTime(), s.getOutTime());
                double hours = duration.toHours();
               // double minutes = duration.toMinutes();
              //  double hoursInMinutes = hours * 60;
               // minutes = (minutes / 60) - hoursInMinutes;
                totalHours += hours;
            }
        }
        Hours addEntry = new Hours( empCode, workingDate, totalHours);
        hoursRepository.save(addEntry);

        return totalHours;
    }

    @Override
    public List<Simulator> getDetails(String empCode, LocalDate workingDate){
        List<Simulator> simulatorList = simulatorRepository.findByEmpCodeAndWorkingDate(empCode,workingDate);
        return simulatorList;
    }
}

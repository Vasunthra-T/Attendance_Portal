package com.quinbay.simulator.service;

import com.quinbay.simulator.api.SimulatorInterface;
import com.quinbay.simulator.model.Hours;
import com.quinbay.simulator.model.Simulator;
import com.quinbay.simulator.model.SimulatorPojo;
import com.quinbay.simulator.repository.HoursRepository;
import com.quinbay.simulator.repository.SimulatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class SimulatorService implements SimulatorInterface {
    @Autowired
    SimulatorRepository simulatorRepository;

    @Autowired
    HoursRepository hoursRepository;

    Hours entry =null;

    @Override
    public ResponseEntity<String> addSimulator(SimulatorPojo simulatorPojo){

        Simulator entry = new Simulator(simulatorPojo.getEmpCode(),simulatorPojo.getInTime().toLocalDate(),simulatorPojo.getInTime(),simulatorPojo.getOutTime());
        simulatorRepository.save(entry);
        return new ResponseEntity<String>("Entry added to simulator successfully",HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Double> calculateHours(String empCode, LocalDate workingDate)   {

        System.out.println(empCode);
        System.out.println(workingDate);
        Optional<Simulator> opt = simulatorRepository.findByEmpCodeAndWorkingDate(empCode,workingDate);
        if (opt.isPresent()) {
            if (workingDate.equals(opt.get().getWorkingDate())) {

                Duration duration = Duration.between(opt.get().getInTime(), opt.get().getOutTime());
                double hours = duration.toHours();
                Hours addEntry = new Hours(1,empCode,workingDate,hours);
                hoursRepository.save(addEntry);
                return new ResponseEntity<Double>(hours, HttpStatus.OK);
            }

        } else {
            return new ResponseEntity<Double>(401.0,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Double>(401.0,HttpStatus.NOT_FOUND);
    }

}

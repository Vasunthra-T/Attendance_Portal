package com.quinbay.simulator.controller;

import com.quinbay.simulator.api.SimulatorInterface;
import com.quinbay.simulator.model.SimulatorPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/simulate")
public class SimulatorController {
    @Autowired
    SimulatorInterface simulatorInterface;

    @PostMapping("addSimulator")
    public ResponseEntity<String> addSimulator(@RequestBody SimulatorPojo simulatorPojo){
        return  simulatorInterface.addSimulator(simulatorPojo);
    }

    @GetMapping("calculateHours")
    public ResponseEntity<Double> calculateHours(@RequestParam String empCode, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate workingDate )
    {

        System.out.println(workingDate.getClass());
        return simulatorInterface.calculateHours(empCode,workingDate);
    }
}

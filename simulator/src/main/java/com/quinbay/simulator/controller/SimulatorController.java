package com.quinbay.simulator.controller;

import com.quinbay.simulator.api.SimulatorService;
import com.quinbay.simulator.model.Simulator;
import com.quinbay.simulator.model.SimulatorRequest;
import com.quinbay.simulator.model.SimulatorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/simulate")
public class SimulatorController {
    @Autowired
    SimulatorService simulatorService;

    @PostMapping("addSimulator")
    public ResponseEntity<String> addSimulator(@RequestBody SimulatorRequest simulatorRequest){
        String result = simulatorService.addSimulator(simulatorRequest);
        HttpStatus status = (result.equals("User doesn't exist")) ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(result, status);
    }

    @GetMapping("calculateHours")
    public ResponseEntity<Double> calculateHours(@RequestParam String empCode, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate workingDate )
    {
        Double result = simulatorService.calculateHours(empCode,workingDate);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("getDetails")
    public List<SimulatorResponse> getDetails(@RequestParam String empCode, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate workingDate){
        return simulatorService.getDetails(empCode,workingDate);
    }
}

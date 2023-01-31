package com.quinbay.simulator.api;

import com.quinbay.simulator.model.SimulatorPojo;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface SimulatorInterface {
    ResponseEntity<String> addSimulator(SimulatorPojo simulatorPojo);
    ResponseEntity<Double> calculateHours(String empCode, LocalDate workingDate);
}

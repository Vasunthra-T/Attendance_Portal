package com.quinbay.simulator.api;

import com.quinbay.simulator.model.Simulator;
import com.quinbay.simulator.model.SimulatorRequest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface SimulatorInterface {
    String addSimulator(SimulatorRequest simulatorRequest);
    Double calculateHours(String empCode, LocalDate workingDate);
    List<Simulator> getDetails(String empCode,LocalDate workingDate);
   String createEntry(SimulatorRequest simulatorRequest);
}

package com.quinbay.simulator.api;

import com.quinbay.simulator.model.Simulator;
import com.quinbay.simulator.model.SimulatorRequest;
import com.quinbay.simulator.model.SimulatorResponse;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface SimulatorService {
    String addSimulator(SimulatorRequest simulatorRequest);
    Double calculateHours(String empCode, LocalDate workingDate);
    List<SimulatorResponse> getDetails(String empCode, LocalDate workingDate);
   String createEntry(SimulatorRequest simulatorRequest);
}

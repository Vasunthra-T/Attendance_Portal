package com.quinbay.simulator.api;

import com.quinbay.simulator.model.Simulator;
import com.quinbay.simulator.model.SimulatorRequest;
import com.quinbay.simulator.model.SimulatorResponse;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface SimulatorService {
    String addSimulator(SimulatorRequest simulatorRequest) throws ParseException;
    Double calculateHours(String empCode, Date workingDate);
    List<SimulatorResponse> getDetails(String empCode, Date workingDate);
   String createEntry(SimulatorRequest simulatorRequest, Date findDate) throws ParseException;
}

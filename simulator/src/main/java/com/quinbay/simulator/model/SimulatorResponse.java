package com.quinbay.simulator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SimulatorResponse {
    LocalDateTime inTime;

    LocalDateTime outTime;
}

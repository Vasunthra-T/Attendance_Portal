package com.quinbay.simulator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SimulatorRequest {
    String empCode;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
    String inTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
    String outTime;
}

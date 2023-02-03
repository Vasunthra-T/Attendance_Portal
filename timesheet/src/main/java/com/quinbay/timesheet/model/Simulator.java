package com.quinbay.timesheet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Simulator implements Serializable {
    Integer id;

    String empCode;

    LocalDate workingDate;
    LocalDateTime inTime;
    LocalDateTime outTime;
}

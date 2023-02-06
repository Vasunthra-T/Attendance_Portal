package com.quinbay.timesheet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Simulator implements Serializable {
    Integer id;

    String empCode;

    Date workingDate;
    Date inTime;
    Date outTime;
}

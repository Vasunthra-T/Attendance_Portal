package com.quinbay.timesheet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hours implements Serializable {
    @Id
    Integer id;
    String emp_code;
    LocalDate workingDate;
    Double hours;
}

package com.quinbay.simulator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="simulator")
public class Simulator implements Serializable {
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    Integer id;

    @Column(name = "emp_code")
    String empCode;

    @Column(name="working_date")
    Date workingDate;

    @Column(name = "in_time")
    Date inTime;

    @Column(name = "out_time")
    Date outTime;


    public Simulator(String empCode,Date workingDate,Date inTime,Date outTime){
        this.empCode = empCode;
        this.workingDate = workingDate;
        this.inTime = inTime;
        this.outTime = outTime;
    }


}




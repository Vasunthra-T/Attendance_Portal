package com.quinbay.simulator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="simulator")
public class Simulator implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(name = "emp_code")
    String empCode;

    @Column(name="working_date")
    LocalDate workingDate;

    @Column(name = "in_time")
    LocalDateTime inTime;

    @Column(name = "out_time")
    LocalDateTime outTime;

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(columnDefinition = "TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3)", name="in_time")
//     Date inTime;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(columnDefinition = "TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3)", name="out_time")
//    Date outTime;

//    @Column(name = "in_time")
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
//    Date inTime;
//
//    @Column(name = "out_time")
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
//    Date outTime;

    public Simulator(String empCode,LocalDate workingDate,LocalDateTime inTime,LocalDateTime outTime){
        this.empCode = empCode;
        this.workingDate = workingDate;
        this.inTime = inTime;
        this.outTime = outTime;
    }


}



//    String dateTimePattern = "dd.MM.yyyy HH:mm z";
//
//    @Column(name = "in_time")
//    DateTimeFormatter inTime = DateTimeFormatter.ofPattern(dateTimePattern);
//
//
//    @Column(name = "out_time")
//    DateTimeFormatter outTime = DateTimeFormatter.ofPattern(dateTimePattern);


//}

package com.quinbay.simulator.repository;

import com.quinbay.simulator.model.Simulator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SimulatorRepository extends JpaRepository<Simulator,Integer> {
    List<Simulator> findByEmpCodeAndWorkingDate(String empCode, Date workingDate);





   // Optional<Simulator> findByEmpCodeAndInTime(String empCode, LocalDate find);

}

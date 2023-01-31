package com.quinbay.simulator.repository;

import com.quinbay.simulator.model.Simulator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SimulatorRepository extends JpaRepository<Simulator,Integer> {
    Optional<Simulator> findByEmpCodeAndWorkingDate(String empCode, LocalDate workingDate);

}

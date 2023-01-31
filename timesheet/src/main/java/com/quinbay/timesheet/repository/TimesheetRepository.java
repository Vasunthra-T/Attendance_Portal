package com.quinbay.timesheet.repository;

import com.quinbay.timesheet.model.Approval;
import com.quinbay.timesheet.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet,Integer> {
    List<Timesheet> findByEmpCodeAndWorkingDateBetween(String empCode, LocalDate fromDate, LocalDate toDate);

    List<Timesheet> findByManagerIdAndStatusAndWorkingDateBetween(String empCode, Approval.Status status, LocalDate fromDate, LocalDate toDate);

    List<Timesheet> findByManagerId(String empCode);

}

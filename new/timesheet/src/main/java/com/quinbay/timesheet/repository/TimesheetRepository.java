package com.quinbay.timesheet.repository;

import com.quinbay.timesheet.model.Approval;
import com.quinbay.timesheet.model.Timesheet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet,Integer> {
    List<Timesheet> findByEmpCodeAndWorkingDateBetween(String empCode, Date fromDate, Date toDate, Pageable paging);

    List<Timesheet> findByEmpCodeAndWorkingDateBetweenOrderByWorkingDateDesc(String empCode, Date fromDate, Date toDate);


    List<Timesheet> findByEmpCodeAndApproval_StatusAndWorkingDateBetween(String empCode, Approval.Status status, Date fromDate, Date toDate);

    List<Timesheet> findByManagerId(String empCode);

    List<Timesheet> findByEmpCode(String empCode);
    //Optional<Timesheet> findByEmpCode(String empCode );

    List<Timesheet> findAll();

    Optional<Timesheet> findByEmpCodeAndWorkingDate(String empCode,Date workingDate);

}

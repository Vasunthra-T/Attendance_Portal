package com.quinbay.email.repository;

import com.quinbay.email.model.TimesheetApproval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TimesheetApprovalRepository extends JpaRepository<TimesheetApproval,Integer> {
    List<TimesheetApproval> findByEmpCodeAndWorkingDateBetween(String empCode, Date fromDate, Date toDate);
}

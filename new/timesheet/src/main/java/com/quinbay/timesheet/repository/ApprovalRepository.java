package com.quinbay.timesheet.repository;

import com.quinbay.timesheet.model.Approval;
import com.quinbay.timesheet.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval,Integer> {
}

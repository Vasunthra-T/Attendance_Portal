package com.quinbay.timesheet.repository;

import com.quinbay.timesheet.model.Approval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval,Integer> {
    Optional<Approval> findByTimesheetId(int timeId);
    Optional<Approval> findById(int id);
}

package com.quinbay.timesheet.repository;

import com.quinbay.timesheet.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Optional<Employee> findByEmailAndPassword(String email, String password);
    Optional<Employee> findByEmpCode(String empCode);
    List<Employee> findAll();
}

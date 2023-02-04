package com.quinbay.simulator.repository;

import com.quinbay.simulator.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
//    @Query(value = "select emp_code from Employee",nativeQuery = true)
//    List<Object> getEmpCode();

   //Optional<Object> findAll();
   List<Employee> findAll();

   Optional<Employee> findByEmpCode(String empCode);

}

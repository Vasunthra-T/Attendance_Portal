package com.quinbay.simulator.service;

import com.quinbay.simulator.api.EmployeeInterface;
import com.quinbay.simulator.model.Employee;
import com.quinbay.simulator.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService implements EmployeeInterface {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<String> showEmployee(){
      //  List<Object> employees = employeeRepository.getEmpCode();
//        Optional<Object> employees = employeeRepository.findAll();
//       List<String> newList = new ArrayList<>() ;
//       if(employees.isPresent()){
//           newList.add(employees.getEmpCode)
//       }
//       newList.add(employees.get().getEmpCoe);


        List<Employee> employees = employeeRepository.findAll();
        List<String> myList = new ArrayList<>();
        for (Employee employee : employees) {
            myList.add(employee.getEmpCode());
        }
        return myList;
   }


}

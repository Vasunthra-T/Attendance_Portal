package com.quinbay.timesheet.api;

import org.springframework.http.ResponseEntity;

public interface EmployeeInterface {
    //ResponseEntity<HashMap<String,String>> authUser(String email, String password);
    ResponseEntity<String> getDetails();

    ResponseEntity<Object> authUser(String email, String password);
}

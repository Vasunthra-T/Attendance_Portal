package com.quinbay.timesheet.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quinbay.timesheet.model.TimesheetApproval;
import com.quinbay.timesheet.repository.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaPublishingService {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    KafkaTemplate kafkaTemplate;

    public void sendTimesheetApprovalDetails(TimesheetApproval message) {

        try{
            kafkaTemplate.send("send.timesheet", this.objectMapper.writeValueAsString(message));
            System.out.println("\n\t Sent kafka message!!!!!!!!!!!!!!!");
        }catch(Exception e){
            System.out.println("Error is building "+ e);
        }

    }
}





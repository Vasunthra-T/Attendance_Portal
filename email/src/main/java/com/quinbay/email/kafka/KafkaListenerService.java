package com.quinbay.email.kafka;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quinbay.email.model.TimesheetApproval;
import com.quinbay.email.repository.TimesheetApprovalRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TimesheetApprovalRepository timesheetApprovalRepository;

    @KafkaListener(topics = "send.timesheet",groupId = "timesheet")
    public void getInvoiceForBilling(ConsumerRecord<?,String> consumerRecord){
         TimesheetApproval timesheetApproval = null;
        try {
            System.out.println("\n\tEntered kafka listener!!!!!!!!!!!!!!!");
            timesheetApproval = objectMapper.readValue(consumerRecord.value(),
                    new TypeReference<TimesheetApproval>() {
                    });

            System.out.println("\n\t--------->Entered<----------");
            System.out.println(timesheetApproval.getEmpCode());
            timesheetApprovalRepository.save(timesheetApproval);
        }catch (Exception e){
            e.printStackTrace();

        }

    }
}

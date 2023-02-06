package com.quinbay.email;

import com.quinbay.email.api.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SchedulerApplication {
    @Autowired
    SummaryService summaryService;

    @Scheduled(cron = "0 30 21 * * 5")//0 30 21 * * 5(every friday)
    public void scheduleTask()
    {
        LocalDate toDate = LocalDate.now();

        summaryService.splitByEmployee(toDate);

    }


}

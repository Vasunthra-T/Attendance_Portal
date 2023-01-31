package com.quinbay.timesheet.api;

import com.quinbay.timesheet.model.EmailDetails;

public interface EmailInterface {
    String sendSimpleMail(EmailDetails details);

    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details);
}

package com.quinbay.email.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class Approval {
    public enum Period {
        FIRST_HALF, SECOND_HALF ,NULL;
    }
    public enum Status{
        APPROVED, WAITING_FOR_APPROVAL;
    }

    Integer id;
    String empCode;
    Double dayCount;
    Period period;
    Status status;

    @OneToOne
    @JoinColumn(name = "timesheet_id", referencedColumnName = "id")
    private Timesheet timesheet;
}

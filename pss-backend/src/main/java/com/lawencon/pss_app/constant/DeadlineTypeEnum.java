package com.lawencon.pss_app.constant;


public enum DeadlineTypeEnum {
    SEND_DATA("send-data", "SD"),
    PAYROLL_REPORT("payroll-report", "PR"),
    BANK_UPLOAD("bank-upload", "BU"),
    PAYROLL_JOURNAL("payroll-journal", "PJ"),
    PAID_OUT("paid-out", "PO");

    private final String deadlineName;
    private final String deadlineCode;
    
    public static DeadlineTypeEnum getById(int id) {
        return DeadlineTypeEnum.values()[id - 1];
    }

    public int getId() {
        return ordinal() + 1;
    }

    DeadlineTypeEnum(String deadlineName, String deadlineCode) {
        this.deadlineName = deadlineName;
        this.deadlineCode = deadlineCode;
    }

    public String getDeadlineName() {
        return deadlineName;
    }

    public String getDeadlineCode() {
        return deadlineCode;
    }
}
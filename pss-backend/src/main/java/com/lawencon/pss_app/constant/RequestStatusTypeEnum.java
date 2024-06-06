package com.lawencon.pss_app.constant;

public enum RequestStatusTypeEnum {
    PENDING("pending", "PD"),
    APPROVED("approved", "AP"),
    REJECTED("rejected", "RJ");

    private final String requestName;
    private final String requestCode;
    
    public static  RequestStatusTypeEnum getById(int id) {
        return  RequestStatusTypeEnum.values()[id - 1];
    }

    public int getId() {
        return ordinal() + 1;
    }

    RequestStatusTypeEnum(String requestName, String requestCode) {
        this.requestName = requestName;
        this.requestCode = requestCode;
    }

    public String getRequestName() {
        return requestName;
    }

    public String getRequestCode() {
        return requestCode;
    }
}
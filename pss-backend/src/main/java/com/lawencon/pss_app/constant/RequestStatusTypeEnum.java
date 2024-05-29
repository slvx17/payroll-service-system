package com.lawencon.pss_app.constant;

public enum RequestStatusTypeEnum {
    PENDING("pending", "PD"),
    APPROVED("approved", "AP"),
    REJECTED("rejected", "RJ");

    private final String requestName;
    private final String requestCode;

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
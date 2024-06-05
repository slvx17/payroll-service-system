package com.lawencon.pss_app.dto.clientassignment;

public class CreateAssignmentReqDto {
    private Long clientId;
    private Long payrollServiceId;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getPayrollServiceId() {
        return payrollServiceId;
    }

    public void setPayrollServiceId(Long payrollServiceId) {
        this.payrollServiceId = payrollServiceId;
    }
}
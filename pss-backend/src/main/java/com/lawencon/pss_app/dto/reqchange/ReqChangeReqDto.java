package com.lawencon.pss_app.dto.reqchange;

import java.time.LocalDate;

public class ReqChangeReqDto {
    private Long dateId;
    private LocalDate newDate;
    private String message;

    public Long getDateId() {
        return dateId;
    }

    public void setDateId(Long dateId) {
        this.dateId = dateId;
    }

    public LocalDate getNewDate() {
        return newDate;
    }

    public void setNewDate(LocalDate newDate) {
        this.newDate = newDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

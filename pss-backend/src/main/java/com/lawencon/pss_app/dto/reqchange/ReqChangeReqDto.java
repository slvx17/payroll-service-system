package com.lawencon.pss_app.dto.reqchange;

import java.time.LocalDate;

import com.lawencon.pss_app.model.Date;

public class ReqChangeReqDto {
    private Date date;
    private LocalDate newDate;
    private String message;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

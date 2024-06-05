package com.lawencon.pss_app.dto.ps;

import java.time.LocalDate;

public class CreateScheduleReqDto {

    private String userEmail;
    private String monthYear;  
    private LocalDate sendDataDate;
    private LocalDate payrollReportDate;
    private LocalDate bankUploadDate;
    private LocalDate payrollJournalDate;
    private LocalDate paidOutDate;

    // Constructors
    public CreateScheduleReqDto() {
    }

    // Getters and Setters
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    public LocalDate getSendDataDate() {
        return sendDataDate;
    }

    public void setSendDataDate(LocalDate sendDataDate) {
        this.sendDataDate = sendDataDate;
    }

    public LocalDate getPayrollReportDate() {
        return payrollReportDate;
    }

    public void setPayrollReportDate(LocalDate payrollReportDate) {
        this.payrollReportDate = payrollReportDate;
    }

    public LocalDate getBankUploadDate() {
        return bankUploadDate;
    }

    public void setBankUploadDate(LocalDate bankUploadDate) {
        this.bankUploadDate = bankUploadDate;
    }

    public LocalDate getPayrollJournalDate() {
        return payrollJournalDate;
    }

    public void setPayrollJournalDate(LocalDate payrollJournalDate) {
        this.payrollJournalDate = payrollJournalDate;
    }

    public LocalDate getPaidOutDate() {
        return paidOutDate;
    }

    public void setPaidOutDate(LocalDate paidOutDate) {
        this.paidOutDate = paidOutDate;
    }
}

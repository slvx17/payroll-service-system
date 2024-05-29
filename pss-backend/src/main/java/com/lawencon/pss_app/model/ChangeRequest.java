package com.lawencon.pss_app.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="change_request")
public class ChangeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "date_id")
    private Date date;

    @Column(name = "new_date", nullable = false)
    private LocalDate newDate;

    @ManyToOne
    @JoinColumn(name = "request_status_id")
    private RequestStatusType requestStatus;

    public ChangeRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public RequestStatusType getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatusType requestStatus) {
        this.requestStatus = requestStatus;
    }
}
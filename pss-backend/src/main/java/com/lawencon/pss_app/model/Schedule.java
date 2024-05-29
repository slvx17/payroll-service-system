package com.lawencon.pss_app.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_assignment_id")
    private ClientAssignment clientAssignment;

    @Column(name = "month_year", nullable = false)
    private LocalDate monthYear;

    public Schedule() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientAssignment getClientAssignment() {
        return clientAssignment;
    }

    public void setClientAssignment(ClientAssignment clientAssignment) {
        this.clientAssignment = clientAssignment;
    }

    public LocalDate getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(LocalDate monthYear) {
        this.monthYear = monthYear;
    }
}
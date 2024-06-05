package com.lawencon.pss_app.model;

import javax.persistence.*;

@Entity
@Table(name="client_assignment")
public class ClientAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne
    @JoinColumn(name = "ps_id")
    private User ps;

    public ClientAssignment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getPs() {
        return ps;
    }

    public void setPs(User ps) {
        this.ps = ps;
    }
}
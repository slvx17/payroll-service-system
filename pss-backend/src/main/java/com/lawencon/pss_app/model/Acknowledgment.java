package com.lawencon.pss_app.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="acknowledgment")
public class Acknowledgment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_sender", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isSender;

    @Column(name = "signed", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean signed;

    @Column(name = "signed_at")
    private LocalDateTime signedAt;

    public Acknowledgment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getIsSender() {
        return isSender;
    }

    public void setIsSender(Boolean isSender) {
        this.isSender = isSender;
    }

    public Boolean getSigned() {
        return signed;
    }

    public void setSigned(Boolean signed) {
        this.signed = signed;
    }

    public LocalDateTime getSignedAt() {
        return signedAt;
    }

    public void setSignedAt(LocalDateTime signedAt) {
        this.signedAt = signedAt;
    }
}
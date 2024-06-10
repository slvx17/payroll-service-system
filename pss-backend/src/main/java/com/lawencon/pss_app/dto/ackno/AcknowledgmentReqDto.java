package com.lawencon.pss_app.dto.ackno;

import java.time.LocalDateTime;

public class AcknowledgmentReqDto {
    private Long documentId;
    private Long userId;
    private Boolean isSender;
    private LocalDateTime signedAt;

    public AcknowledgmentReqDto() {}

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getIsSender() {
        return isSender;
    }

    public void setIsSender(Boolean isSender) {
        this.isSender = isSender;
    }

    public LocalDateTime getSignedAt() {
        return signedAt;
    }

    public void setSignedAt(LocalDateTime signedAt) {
        this.signedAt = signedAt;
    }
}

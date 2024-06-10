package com.lawencon.pss_app.dto.notification;

import java.time.LocalDateTime;

public class NotificationResDto {
	private Long id;
	private LocalDateTime DateTimeSent;
	private String SenderName;
	private String ReceiverName;
	private String Message;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getDateTimeSent() {
		return DateTimeSent;
	}
	public void setDateTimeSent(LocalDateTime dateTimeSent) {
		DateTimeSent = dateTimeSent;
	}
	public String getSenderName() {
		return SenderName;
	}
	public void setSenderName(String senderName) {
		SenderName = senderName;
	}
	public String getReceiverName() {
		return ReceiverName;
	}
	public void setReceiverName(String receiverName) {
		ReceiverName = receiverName;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
}

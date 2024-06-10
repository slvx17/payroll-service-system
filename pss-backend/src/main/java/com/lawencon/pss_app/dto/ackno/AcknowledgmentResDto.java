package com.lawencon.pss_app.dto.ackno;

public class AcknowledgmentResDto {
	private Long id;
	private String message;
	
	public AcknowledgmentResDto(Long id, String message) {
		this.id = id;
		this.message = message;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}

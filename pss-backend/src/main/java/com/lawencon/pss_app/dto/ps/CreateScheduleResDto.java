package com.lawencon.pss_app.dto.ps;

public class CreateScheduleResDto {

	private Long scheduleId;
	private String message;
	
	public CreateScheduleResDto(Long scheduleId, String message) {
		super();
		this.scheduleId = scheduleId;
		this.message = message;
	}
	public Long getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}

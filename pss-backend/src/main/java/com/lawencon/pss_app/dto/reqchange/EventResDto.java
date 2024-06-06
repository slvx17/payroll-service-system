package com.lawencon.pss_app.dto.reqchange;

import java.util.List;

import com.lawencon.pss_app.model.Date;

public class EventResDto {
	private List<Date> events;
	private String message;

	public EventResDto(List<Date> events, String message) {
		this.events = events;
		this.message = message;
	}

	public List<Date> getEvents() {
		return events;
	}

	public void setEvents(List<Date> events) {
		this.events = events;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

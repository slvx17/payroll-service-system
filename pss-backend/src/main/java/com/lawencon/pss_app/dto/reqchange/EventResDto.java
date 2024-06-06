package com.lawencon.pss_app.dto.reqchange;

import java.util.List;

import com.lawencon.pss_app.model.Date;

public class EventResDto {
	private List<Date> events;

	public EventResDto(List<Date> events) {
		this.events = events;
	}

	public List<Date> getEvents() {
		return events;
	}

	public void setEvents(List<Date> events) {
		this.events = events;
	}
	
	
}

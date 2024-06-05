package com.lawencon.pss_app.dto_calendar;

import java.time.LocalDate;
import java.util.List;

public class CalendarResDto {
	private List<LocalDate> Deadline;
	private List<String> DeadlineType;
	
	public List<LocalDate> getDeadline() {
		return Deadline;
	}
	public void setDeadline(List<LocalDate> deadline) {
		Deadline = deadline;
	}
	public List<String> getDeadlineType() {
		return DeadlineType;
	}
	public void setDeadlineType(List<String> deadlineType) {
		DeadlineType = deadlineType;
	}
}

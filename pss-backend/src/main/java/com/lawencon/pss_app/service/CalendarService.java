package com.lawencon.pss_app.service;

import com.lawencon.pss_app.dto.calendar.CalendarReqDto;
import com.lawencon.pss_app.dto.calendar.CalendarResDto;
import com.lawencon.pss_app.dto.ps.CreateScheduleReqDto;
import com.lawencon.pss_app.dto.ps.CreateScheduleResDto;

public interface CalendarService {
	
	CalendarResDto getAllDeadlines(CalendarReqDto reqDto);

	CreateScheduleResDto createScheduleAndDates(CreateScheduleReqDto createScheduleReqDto);
}

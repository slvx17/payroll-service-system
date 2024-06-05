package com.lawencon.pss_app.service;

import java.util.List;

import com.lawencon.pss_app.dto_calendar.CalendarReqDto;
import com.lawencon.pss_app.dto_calendar.CalendarResDto;
import com.lawencon.pss_app.model.Date;

public interface CalendarService {
	
	CalendarResDto getAllDeadlines(CalendarReqDto reqDto);
}

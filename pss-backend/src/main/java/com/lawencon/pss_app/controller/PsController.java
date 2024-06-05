package com.lawencon.pss_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.pss_app.dto.ps.CreateScheduleReqDto;
import com.lawencon.pss_app.dto.ps.CreateScheduleResDto;
import com.lawencon.pss_app.service.CalendarService;

@RestController
@RequestMapping("/CL")
public class PsController {
	
private final CalendarService calendarService;
	
	public PsController(CalendarService calendarService) {
		this.calendarService = calendarService;
	}

	@PostMapping("/createschedule")
	public ResponseEntity<CreateScheduleResDto> createSchedule(@RequestBody CreateScheduleReqDto param){
		CreateScheduleResDto result = calendarService.createScheduleAndDates(param);
		return new ResponseEntity<CreateScheduleResDto>(result, HttpStatus.CREATED);
	}
}

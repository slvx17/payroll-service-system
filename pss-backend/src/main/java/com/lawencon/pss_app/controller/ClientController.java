package com.lawencon.pss_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.pss_app.dto_calendar.CalendarReqDto;
import com.lawencon.pss_app.dto_calendar.CalendarResDto;
import com.lawencon.pss_app.service.CalendarService;

@RestController
public class ClientController {

	private final AuthenticationManager authenticationManager;
	private final CalendarService calendarService;
	
	public ClientController(AuthenticationManager authenticationManager, 
			CalendarService calendarService) {
		this.calendarService = calendarService;
		this.authenticationManager = authenticationManager;
	}
	
	@PostMapping("/CL/getCalendar")
	public ResponseEntity<CalendarResDto> caelndarResDto(@RequestBody CalendarReqDto param){
		CalendarResDto result = calendarService.getAllDeadlines(param);
		return new ResponseEntity<CalendarResDto>(result, HttpStatus.CREATED);
	}
}

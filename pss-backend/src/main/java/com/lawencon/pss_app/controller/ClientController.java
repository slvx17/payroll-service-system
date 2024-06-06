package com.lawencon.pss_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.pss_app.dto.calendar.CalendarReqDto;
import com.lawencon.pss_app.dto.calendar.CalendarResDto;
import com.lawencon.pss_app.service.CalendarService;

@RestController
@RequestMapping("/CL")
public class ClientController {

	private final CalendarService calendarService;
	
	public ClientController(AuthenticationManager authenticationManager, 
			CalendarService calendarService) {
		this.calendarService = calendarService;
	}
	
	@PostMapping("/getCalendar")
	public ResponseEntity<CalendarResDto> calendarResDto(@RequestBody CalendarReqDto param){
		CalendarResDto result = calendarService.getAllDeadlines(param);
		return new ResponseEntity<CalendarResDto>(result, HttpStatus.CREATED);
	}
}

package com.lawencon.pss_app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.pss_app.dto.ps.CreateScheduleReqDto;
import com.lawencon.pss_app.dto.ps.CreateScheduleResDto;
import com.lawencon.pss_app.dto.ps.GetClientsResDto;
import com.lawencon.pss_app.model.User;
import com.lawencon.pss_app.service.CalendarService;
import com.lawencon.pss_app.service.UserService;

@RestController
@RequestMapping("/CL")
public class PsController {
	
private final UserService userService;	
private final CalendarService calendarService;
	
	public PsController(CalendarService calendarService, UserService userService) {
		this.userService = userService;
		this.calendarService = calendarService;
	}
	
	@PostMapping("/getclientsbyps")
	public ResponseEntity<List<User>> getClientsByPs(@RequestBody String email){
		List<User> result = userService.getClientsByPs(email);
		return ResponseEntity.ok(result); 
	}

	@PostMapping("/createschedule")
	public ResponseEntity<CreateScheduleResDto> createSchedule(@RequestBody CreateScheduleReqDto param){
		CreateScheduleResDto result = calendarService.createScheduleAndDates(param);
		return new ResponseEntity<CreateScheduleResDto>(result, HttpStatus.CREATED);
	}
}

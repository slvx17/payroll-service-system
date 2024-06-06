package com.lawencon.pss_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.pss_app.dto.calendar.CalendarReqDto;
import com.lawencon.pss_app.dto.calendar.CalendarResDto;
import com.lawencon.pss_app.dto.reqchange.EventReqDto;
import com.lawencon.pss_app.dto.reqchange.EventResDto;
import com.lawencon.pss_app.dto.reqchange.ReqChangeReqDto;
import com.lawencon.pss_app.dto.reqchange.ReqChangeResDto;
import com.lawencon.pss_app.model.ClientAssignment;
import com.lawencon.pss_app.model.User;
import com.lawencon.pss_app.service.CalendarService;
import com.lawencon.pss_app.service.ChangeRequestService;
import com.lawencon.pss_app.service.ClientAssignmentService;
import com.lawencon.pss_app.service.UserService;

@RestController
@RequestMapping("/CL")
public class ClientController {

	private final CalendarService calendarService;
	private ChangeRequestService changeRequestService;
	private UserService userService;
	private ClientAssignmentService assignmentService;
	
	public ClientController(
			ChangeRequestService changeRequestService, 
			CalendarService calendarService,
			UserService userService,
			ClientAssignmentService assignmentService) {
		this.calendarService = calendarService;
		this.changeRequestService = changeRequestService;
		this.userService = userService;
		this.assignmentService = assignmentService;
	}
	
	@PostMapping("/getCalendar")
	public ResponseEntity<CalendarResDto> calendarResDto(@RequestBody CalendarReqDto param){
		CalendarResDto result = calendarService.getAllDeadlines(param);
		return new ResponseEntity<CalendarResDto>(result, HttpStatus.CREATED);
	}
	
	@PostMapping("/requestchange")
	public ResponseEntity<ReqChangeResDto> requestChange(@RequestBody ReqChangeReqDto reqDto) {
        ReqChangeResDto result = changeRequestService.processChangeRequest(reqDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
	
	@PostMapping("/getevents")
	public ResponseEntity<EventResDto> requestChange(@RequestBody EventReqDto reqDto) {
        EventResDto result = changeRequestService.getEvents(reqDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
	
	@GetMapping("/getbyid")
    public ResponseEntity<ClientAssignment> getUserById(@RequestParam Long id) {
        User user = userService.getById(id);
        ClientAssignment assignment = assignmentService.getByClient(user);
        return ResponseEntity.ok(assignment);
    }
	
	
}

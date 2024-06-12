package com.lawencon.pss_app.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.pss_app.dto.ps.CreateScheduleReqDto;
import com.lawencon.pss_app.dto.ps.CreateScheduleResDto;
import com.lawencon.pss_app.dto.reqchange.GetChangeReqDto;
import com.lawencon.pss_app.dto.reqchange.GetChangeResDto;
import com.lawencon.pss_app.dto.reqchange.UpdateChangeReqDto;
import com.lawencon.pss_app.dto.reqchange.UpdateChangeResDto;
import com.lawencon.pss_app.model.Notification;
import com.lawencon.pss_app.service.CalendarService;
import com.lawencon.pss_app.service.ChangeRequestService;
import com.lawencon.pss_app.service.NotificationService;
import com.lawencon.pss_app.service.UserService;

@RestController
@RequestMapping("/CL")
public class PsController {
	
	private final CalendarService calendarService;
	private ChangeRequestService changeRequestService;
	private NotificationService notificationService;
	private UserService userService;
	
	public PsController(CalendarService calendarService,
			ChangeRequestService changeRequestService, 
			NotificationService notificationService,
			UserService userService) {
		this.calendarService = calendarService;
		this.changeRequestService = changeRequestService;
		this.notificationService = notificationService;
		this.userService = userService;
	}

	@PostMapping("/createschedule")
	public ResponseEntity<CreateScheduleResDto> createSchedule(@RequestBody CreateScheduleReqDto param){
        Notification notification = new Notification();
        notification.setMessage("New schedule and deadlines created for " + param.getMonthYear());
        notification.setSendAt(Timestamp.valueOf(LocalDateTime.now()));   
        notification.setReceiver(userService.getByEmail((param.getUserEmail())));
        notification.setSender(null);
        notification.setIsRead(false);
        notificationService.create(notification);
		CreateScheduleResDto result = calendarService.createScheduleAndDates(param);
		return new ResponseEntity<CreateScheduleResDto>(result, HttpStatus.CREATED);
	}
	
	@PostMapping("/updatechangereq")
    public ResponseEntity<UpdateChangeResDto> updateChangeRequest(@RequestBody UpdateChangeReqDto requestDto) {
        Notification notification = new Notification();
        notification.setMessage("Received update change request");
        notification.setSendAt(Timestamp.valueOf(LocalDateTime.now()));   
        notification.setReceiver(null); // <------------- FIX
        notification.setSender(null);
        notification.setIsRead(false);
        notificationService.create(notification);
        UpdateChangeResDto responseDto = changeRequestService.updateReqChange(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/getchangereq")
    public ResponseEntity<GetChangeResDto> getChangeRequest(@RequestBody GetChangeReqDto requestDto) {
        GetChangeResDto responseDto = changeRequestService.getChangeByType(requestDto);
        return ResponseEntity.ok(responseDto);
    }
}

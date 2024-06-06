package com.lawencon.pss_app.controller;

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
import com.lawencon.pss_app.service.CalendarService;
import com.lawencon.pss_app.service.ChangeRequestService;

@RestController
@RequestMapping("/CL")
public class PsController {
	
	private final CalendarService calendarService;
	private ChangeRequestService changeRequestService;
	
	public PsController(CalendarService calendarService,
			ChangeRequestService changeRequestService) {
		this.calendarService = calendarService;
		this.changeRequestService = changeRequestService;
	}

	@PostMapping("/createschedule")
	public ResponseEntity<CreateScheduleResDto> createSchedule(@RequestBody CreateScheduleReqDto param){
		CreateScheduleResDto result = calendarService.createScheduleAndDates(param);
		return new ResponseEntity<CreateScheduleResDto>(result, HttpStatus.CREATED);
	}
	
	@PostMapping("/updatechangereq")
    public ResponseEntity<UpdateChangeResDto> updateChangeRequest(@RequestBody UpdateChangeReqDto requestDto) {
        UpdateChangeResDto responseDto = changeRequestService.updateReqChange(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/getchangereq")
    public ResponseEntity<GetChangeResDto> getChangeRequest(@RequestBody GetChangeReqDto requestDto) {
        GetChangeResDto responseDto = changeRequestService.getChangeByType(requestDto);
        return ResponseEntity.ok(responseDto);
    }
}

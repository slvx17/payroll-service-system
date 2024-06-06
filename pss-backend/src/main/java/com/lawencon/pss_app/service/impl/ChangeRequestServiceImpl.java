package com.lawencon.pss_app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.pss_app.constant.RequestStatusTypeEnum;
import com.lawencon.pss_app.dao.ChangeRequestDao;
import com.lawencon.pss_app.dao.DateDao;
import com.lawencon.pss_app.dao.ScheduleDao;
import com.lawencon.pss_app.dto.reqchange.EventReqDto;
import com.lawencon.pss_app.dto.reqchange.EventResDto;
import com.lawencon.pss_app.dto.reqchange.GetChangeReqDto;
import com.lawencon.pss_app.dto.reqchange.GetChangeResDto;
import com.lawencon.pss_app.dto.reqchange.ReqChangeReqDto;
import com.lawencon.pss_app.dto.reqchange.ReqChangeResDto;
import com.lawencon.pss_app.dto.reqchange.UpdateChangeReqDto;
import com.lawencon.pss_app.dto.reqchange.UpdateChangeResDto;
import com.lawencon.pss_app.model.ChangeRequest;
import com.lawencon.pss_app.model.Date;
import com.lawencon.pss_app.model.RequestStatusType;
import com.lawencon.pss_app.service.ChangeRequestService;

@Service
public class ChangeRequestServiceImpl implements ChangeRequestService{
	
	private ChangeRequestDao changeReqDao;
	private DateDao dateDao;
	private ScheduleDao scheduleDao;

	public ChangeRequestServiceImpl(ChangeRequestDao changeReqDao, 
			DateDao dateDao,
			ScheduleDao scheduleDao
			) {
		this.changeReqDao = changeReqDao;
		this.dateDao = dateDao;
		this.scheduleDao = scheduleDao;
	}

	@Override
	public ReqChangeResDto processChangeRequest(ReqChangeReqDto req) {
		ChangeRequest changeReq = new ChangeRequest();
		changeReq.setDate(req.getDate());
		changeReq.setNewDate(req.getNewDate());
		changeReq.setMessage(req.getMessage());
		changeReq.setRequestStatus(new RequestStatusType(RequestStatusTypeEnum.getById(1)));
		changeReq = changeReqDao.create(changeReq);
		
		 return new ReqChangeResDto(changeReq.getId(),"User created successfully!");
	}
	
	@Override 
	public EventResDto getEvents(EventReqDto req) {
		List<Date> dates = dateDao.findBySchedule(scheduleDao.findByAssignmentAndMonth(req.getAssignmentId(), req.getMonthYear()));
		
		return new EventResDto(dates);
	}
	
	@Override
	public UpdateChangeResDto updateReqChange(UpdateChangeReqDto req) {
		ChangeRequest chReq = changeReqDao.findById(req.getReqId());
		if(req.getStatus() == 1) {
			chReq.setRequestStatus(new RequestStatusType(RequestStatusTypeEnum.getById(2)));
			Date date = chReq.getDate();
			date.setDeadlineDate(chReq.getNewDate());
			dateDao.update(date);
		}
		else chReq.setRequestStatus(new RequestStatusType(RequestStatusTypeEnum.getById(3)));
		
		chReq = changeReqDao.update(chReq);
		
		return new UpdateChangeResDto(chReq.getId(), "Succesfully updated!");
	}
	
	@Override 
	public GetChangeResDto getChangeByType(GetChangeReqDto req) {
		List<ChangeRequest> res = changeReqDao.findByRequestStatus(new RequestStatusType(RequestStatusTypeEnum.getById(req.getTypeId())));
		return new GetChangeResDto(res);
	}

}

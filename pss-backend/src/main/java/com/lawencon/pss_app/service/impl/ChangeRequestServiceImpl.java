package com.lawencon.pss_app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.pss_app.dao.ChangeRequestDao;
import com.lawencon.pss_app.dao.DateDao;
import com.lawencon.pss_app.dao.RequestStatusTypeDao;
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
import com.lawencon.pss_app.service.ChangeRequestService;

@Service
public class ChangeRequestServiceImpl implements ChangeRequestService{
	
	private ChangeRequestDao changeReqDao;
	private DateDao dateDao;
	private ScheduleDao scheduleDao;
	
	@Autowired
	private RequestStatusTypeDao requestStatusTypeDao;

	public ChangeRequestServiceImpl(ChangeRequestDao changeReqDao, 
			DateDao dateDao,
			ScheduleDao scheduleDao
			) {
		this.changeReqDao = changeReqDao;
		this.dateDao = dateDao;
		this.scheduleDao = scheduleDao;
	}

	@Transactional
	@Override
	public ReqChangeResDto processChangeRequest(ReqChangeReqDto req) {
		ChangeRequest changeReq = new ChangeRequest();
		changeReq.setDate(dateDao.findById(req.getDateId()));
		changeReq.setNewDate(req.getNewDate());
		changeReq.setMessage(req.getMessage());
		changeReq.setRequestStatus(requestStatusTypeDao.findById(1));
		changeReq = changeReqDao.create(changeReq);
		
		 return new ReqChangeResDto(changeReq.getId(),"User created successfully!");
	}
	
	@Override 
	public EventResDto getEvents(EventReqDto req) {
		List<Date> dates = dateDao.findBySchedule(scheduleDao.findByAssignmentAndMonth(req.getAssignmentId(), req.getMonthYear()));
		String mess="Found dates";
		if(dates.isEmpty())mess="Not found";
		
		return new EventResDto(dates, mess);
	}
	
	@Transactional
	@Override
	public UpdateChangeResDto updateReqChange(UpdateChangeReqDto req) {
		ChangeRequest chReq = changeReqDao.findById(req.getReqId());
		if(req.getStatus() == 1) {
			chReq.setRequestStatus(requestStatusTypeDao.findById(2));
			Date date = chReq.getDate();
			date.setDeadlineDate(chReq.getNewDate());
			dateDao.update(date);
		}
		else chReq.setRequestStatus(requestStatusTypeDao.findById(3));
		
		chReq = changeReqDao.update(chReq);
		
		return new UpdateChangeResDto(chReq.getId(), "Succesfully updated!");
	}
	
	@Override 
	public GetChangeResDto getChangeByType(GetChangeReqDto req) {
		List<ChangeRequest> res = changeReqDao.findByRequestStatus(requestStatusTypeDao.findById(req.getTypeId()));
		return new GetChangeResDto(res);
	}

}

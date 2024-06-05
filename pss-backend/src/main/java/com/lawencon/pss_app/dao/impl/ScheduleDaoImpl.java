package com.lawencon.pss_app.dao.impl;

import org.springframework.stereotype.Repository;

import com.lawencon.pss_app.dao.ScheduleDao;
import com.lawencon.pss_app.model.ClientAssignment;
import com.lawencon.pss_app.model.Schedule;
import com.lawencon.pss_app.repo.ScheduleRepo;

@Repository
public class ScheduleDaoImpl implements ScheduleDao{
	private final ScheduleRepo scheduleRepo;
	
	public ScheduleDaoImpl (ScheduleRepo scheduleRepo) {
		this.scheduleRepo = scheduleRepo;
	}
	
	@Override
	public Schedule getByClientAssignment(ClientAssignment clientAssignment) {
		return scheduleRepo.findByClientAssignment(clientAssignment);
	}

}

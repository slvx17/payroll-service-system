package com.lawencon.pss_app.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.pss_app.dao.DateDao;
import com.lawencon.pss_app.model.Date;
import com.lawencon.pss_app.model.Schedule;
import com.lawencon.pss_app.repo.DateRepo;

@Repository
public class DateDaoImpl implements DateDao {
	private final DateRepo dateRepo;
	
	public DateDaoImpl (DateRepo dateRepo) {
		this.dateRepo = dateRepo;
	}

	@Override
	public List<Date> getBySchedule(Schedule schedule) {
		return dateRepo.findBySchedule(schedule);
	}
}

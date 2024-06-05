package com.lawencon.pss_app.dao;

import java.util.List;

import com.lawencon.pss_app.model.Date;
import com.lawencon.pss_app.model.Schedule;

public interface DateDao {
	List<Date> getBySchedule(Schedule schedule);
}

package com.lawencon.pss_app.dao;

import java.util.List;

import com.lawencon.pss_app.model.ClientAssignment;
import com.lawencon.pss_app.model.Schedule;

public interface ScheduleDao {
	List<Schedule> getByClientAssignment(ClientAssignment clientAssignment);
}

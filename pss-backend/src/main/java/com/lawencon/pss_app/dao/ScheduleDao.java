package com.lawencon.pss_app.dao;

import com.lawencon.pss_app.model.ClientAssignment;
import com.lawencon.pss_app.model.Schedule;

public interface ScheduleDao {
	Schedule getByClientAssignment(ClientAssignment clientAssignment);
}

package com.lawencon.pss_app.dao;

import java.util.List;
import java.util.Optional;

import com.lawencon.pss_app.model.ClientAssignment;
import com.lawencon.pss_app.model.Schedule;

public interface ScheduleDao {
	List<Schedule> getByClientAssignment(ClientAssignment clientAssignment);

	Schedule create(Schedule schedule);

	Schedule update(Schedule schedule);

	Optional<Schedule> findById(Long id);

	void delete(Long id);
}

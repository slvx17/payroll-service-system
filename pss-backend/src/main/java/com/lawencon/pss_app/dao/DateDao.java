package com.lawencon.pss_app.dao;

import java.util.List;
import java.util.Optional;

import com.lawencon.pss_app.model.Date;
import com.lawencon.pss_app.model.Schedule;

public interface DateDao {
	List<Date> getBySchedule(Schedule schedule);

	Date create(Date date);

	Date update(Date date);

	Optional<Date> findById(Long id);

	void delete(Long id);
}

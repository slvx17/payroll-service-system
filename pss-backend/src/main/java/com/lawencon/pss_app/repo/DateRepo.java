package com.lawencon.pss_app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.pss_app.model.Date;
import com.lawencon.pss_app.model.Schedule;

@Repository
public interface DateRepo extends JpaRepository<Date, Long> {
	List<Date> findBySchedule(Schedule schedule);
}

package com.lawencon.pss_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.pss_app.model.ClientAssignment;
import com.lawencon.pss_app.model.Schedule;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Long>{
	Schedule findByClientAssignment(ClientAssignment clientAssignment);
}
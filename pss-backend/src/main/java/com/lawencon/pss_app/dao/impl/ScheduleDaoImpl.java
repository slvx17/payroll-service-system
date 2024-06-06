package com.lawencon.pss_app.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.pss_app.dao.ScheduleDao;
import com.lawencon.pss_app.model.ClientAssignment;
import com.lawencon.pss_app.model.Schedule;
import com.lawencon.pss_app.repo.ScheduleRepo;

@Repository
public class ScheduleDaoImpl implements ScheduleDao {
    private final ScheduleRepo scheduleRepo;
    
    public ScheduleDaoImpl(ScheduleRepo scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }
    
    @Override
    public List<Schedule> findByClientAssignment(ClientAssignment clientAssignment) {
        return scheduleRepo.findByClientAssignment(clientAssignment);
    }
    
    @Override
    public Optional<Schedule> findById(Long id) {
        return scheduleRepo.findById(id);
    }
    
    @Override
    public Schedule findByAssignmentAndMonth(Long assignId, String monthYear) {
    	return scheduleRepo.findByClientAssignment_IdAndMonthYear(assignId, monthYear);
    }
    
    @Override
    public Schedule findByMonthYear(String monthYear) {
    	return scheduleRepo.findByMonthYear(monthYear);
    }

    @Override
    public Schedule create(Schedule schedule) {
        return scheduleRepo.save(schedule);
    }

    @Override
    public Schedule update(Schedule schedule) {
        return scheduleRepo.save(schedule);  
    }

    @Override
    public void delete(Long id) {
        scheduleRepo.deleteById(id);
    }
}

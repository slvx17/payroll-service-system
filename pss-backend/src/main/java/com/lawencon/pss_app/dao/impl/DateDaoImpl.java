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
    
    public DateDaoImpl(DateRepo dateRepo) {
        this.dateRepo = dateRepo;
    }
    
    @Override
    public List<Date> findBySchedule(Schedule schedule) {
        return dateRepo.findBySchedule(schedule);
    }

    @Override
    public Date create(Date date) {
        return dateRepo.save(date);
    }

    @Override
    public Date update(Date date) {
        return dateRepo.save(date); 
    }

    @Override
    public Date findById(Long id) {
        return dateRepo.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        dateRepo.deleteById(id);
    }
}

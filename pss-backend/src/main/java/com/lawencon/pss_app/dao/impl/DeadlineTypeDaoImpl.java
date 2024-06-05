package com.lawencon.pss_app.dao.impl;

import org.springframework.stereotype.Repository;

import com.lawencon.pss_app.dao.DeadlineTypeDao;
import com.lawencon.pss_app.model.DeadlineType;
import com.lawencon.pss_app.repo.DeadlineTypeRepo;

@Repository
public class DeadlineTypeDaoImpl implements DeadlineTypeDao {

    private DeadlineTypeRepo deadlineTypeRepo;

    public DeadlineTypeDaoImpl(DeadlineTypeRepo deadlineTypeRepo) {
		this.deadlineTypeRepo = deadlineTypeRepo;
	}

	@Override
    public DeadlineType create(DeadlineType deadlineType) {
        return deadlineTypeRepo.save(deadlineType); 
    }

    @Override
    public DeadlineType findById(Integer id) {
        return deadlineTypeRepo.findById(id).orElse(null); 
    }

    @Override
    public void delete(Integer id) {
        deadlineTypeRepo.deleteById(id); 
    }
}

package com.lawencon.pss_app.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.pss_app.dao.ChangeRequestDao;
import com.lawencon.pss_app.model.ChangeRequest;
import com.lawencon.pss_app.model.RequestStatusType;
import com.lawencon.pss_app.repo.ChangeRequestRepo;

@Repository
public class ChangeRequestDaoImpl implements ChangeRequestDao {



	private ChangeRequestRepo changeRequestRepo;
	
	public ChangeRequestDaoImpl(ChangeRequestRepo changeRequestRepo) {
		this.changeRequestRepo = changeRequestRepo;
	}

    @Override
    public ChangeRequest create(ChangeRequest changeRequest) {
        return changeRequestRepo.save(changeRequest);
    }

    @Override
    public ChangeRequest update(ChangeRequest changeRequest) {
        if (changeRequest != null && changeRequest.getId() != null) {
            return changeRequestRepo.save(changeRequest);
        }
        throw new IllegalArgumentException("ChangeRequest ID cannot be null");
    }

    @Override
    public ChangeRequest findById(Long id) {
        return changeRequestRepo.findById(id).orElse(null);
    }

    @Override
    public List<ChangeRequest> findByRequestStatus(RequestStatusType requestStatus) {
        return changeRequestRepo.findByRequestStatus_Id(requestStatus.getId());
    }

    @Override
    public void delete(Long id) {
        changeRequestRepo.deleteById(id);
    }
}

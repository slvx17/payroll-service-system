package com.lawencon.pss_app.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.pss_app.dao.RequestStatusTypeDao;
import com.lawencon.pss_app.model.RequestStatusType;
import com.lawencon.pss_app.repo.RequestStatusTypeRepo;

@Repository
public class RequestStatusTypeDaoImpl implements RequestStatusTypeDao {

    @Autowired
    private RequestStatusTypeRepo requestStatusTypeRepo;

    @Override
    public RequestStatusType create(RequestStatusType requestStatusType) {
        return requestStatusTypeRepo.save(requestStatusType);
    }

    @Override
    public RequestStatusType findById(Integer id) {
        return requestStatusTypeRepo.findById(id).orElse(null);
    }

    @Override
    public RequestStatusType update(RequestStatusType requestStatusType) {
        if (requestStatusType.getId() == null ) {
            throw new RuntimeException("Status type not found or ID is null");
        }
        return requestStatusTypeRepo.save(requestStatusType);
    }

    @Override
    public void delete(Integer id) {
        requestStatusTypeRepo.deleteById(id);
    }
}

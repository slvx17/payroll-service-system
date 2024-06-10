package com.lawencon.pss_app.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.pss_app.dao.AcknowledgmentDao;
import com.lawencon.pss_app.model.Acknowledgment;
import com.lawencon.pss_app.model.Document;
import com.lawencon.pss_app.repo.AcknowledgementRepo;

@Service
public class AcknowledgmentDaoImpl implements AcknowledgmentDao {

	@Autowired
	private AcknowledgementRepo acknoRepo;
	
	@Override
	public Acknowledgment create(Acknowledgment ackno) {
		return acknoRepo.save(ackno);
	}
	
	@Override
	public Acknowledgment findById(Long id) {
		return acknoRepo.findById(id).orElse(null);
	}
	
	@Override
	public List<Acknowledgment> findByDocument(Document document) {
		return acknoRepo.findByDocument(document);
	}
	
	@Override
	public Acknowledgment update(Acknowledgment ackno) {
		if (ackno != null && ackno.getId() != null) {
            return acknoRepo.save(ackno);
        }
        throw new IllegalArgumentException("acknowledgment ID cannot be null");
	}
	
	@Override
    public void delete(Long id) {
        acknoRepo.deleteById(id);
    }
}

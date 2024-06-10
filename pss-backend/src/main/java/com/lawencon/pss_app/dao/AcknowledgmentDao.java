package com.lawencon.pss_app.dao;

import java.util.List;

import com.lawencon.pss_app.model.Acknowledgment;
import com.lawencon.pss_app.model.Document;

public interface AcknowledgmentDao {

	void delete(Long id);

	Acknowledgment create(Acknowledgment ackno);

	Acknowledgment findById(Long id);

	List<Acknowledgment> findByDocument(Document document);

	Acknowledgment update(Acknowledgment ackno);

}

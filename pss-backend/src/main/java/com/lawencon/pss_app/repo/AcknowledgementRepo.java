package com.lawencon.pss_app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lawencon.pss_app.model.Acknowledgment;
import com.lawencon.pss_app.model.Document;

public interface AcknowledgementRepo extends JpaRepository<Acknowledgment,Long> {

	List<Acknowledgment> findByDocument(Document document);

}

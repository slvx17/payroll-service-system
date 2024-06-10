package com.lawencon.pss_app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.pss_app.model.Document;
import com.lawencon.pss_app.model.File;

@Repository
public interface DocumentRepo extends JpaRepository<Document, Long> {

	Document findByFile(File file);

	@Query("SELECT d FROM Document d " +
	           "JOIN d.date dt " +
	           "JOIN dt.schedule s " +
	           "JOIN s.clientAssignment ca " +
	           "WHERE ca.client.id = :clientId AND d.uploadedBy.id != :clientId")
	List<Document> findDocumentsByClient(Long clientId);
	
	
	@Query("SELECT d FROM Document d " +
			"JOIN d.date dt " +
			"JOIN dt.schedule s " +
			"JOIN s.clientAssignment ca " +
			"WHERE ca.ps.id = :psId AND d.uploadedBy.id != :psId")
	List<Document> findDocumentsByPs(Long psId);
}

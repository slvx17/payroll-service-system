package com.lawencon.pss_app.dao;

import java.util.List;

import com.lawencon.pss_app.model.Document;
import com.lawencon.pss_app.model.File;

public interface DocumentDao {

	Document create(Document document);

	Document findById(Long id);
	
	Document findByFile(File file);

	Document update(Document document);

	void delete(Long id);

	List<Document> findDocumentsByClient(Long clientId);

	List<Document> findDocumentsByPs(Long psId);

}

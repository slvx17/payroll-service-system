package com.lawencon.pss_app.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.pss_app.dao.DocumentDao;
import com.lawencon.pss_app.model.Document;
import com.lawencon.pss_app.model.File;
import com.lawencon.pss_app.repo.DocumentRepo;

@Service
public class DocumentDaoImpl implements DocumentDao {

	 @Autowired
	    private DocumentRepo documentRepo;

	    @Override
	    public Document create(Document document) {
	        return documentRepo.save(document);  
	    }

	    @Override
	    public Document findById(Long id) {
	        return documentRepo.findById(id).orElse(null);
	    }
	    
	    @Override
	    public Document findByFile(File file) {
	        return documentRepo.findByFile(file);
	    }
	    
	    @Override
	    public List<Document> findDocumentsByClient(Long clientId){
	    	return documentRepo.findDocumentsByClient(clientId);
	    }
	    
	    @Override
	    public List<Document> findDocumentsByPs(Long psId){
	    	return documentRepo.findDocumentsByClient(psId);
	    }

	    @Override
	    public Document update(Document document) {
	        return documentRepo.save(document);
	    }

	    @Override
	    public void delete(Long id) {
	        documentRepo.deleteById(id);
	    }
	    
}

package com.lawencon.pss_app.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.pss_app.dao.FileDao;
import com.lawencon.pss_app.model.File;
import com.lawencon.pss_app.repo.FileRepo;

@Service
public class FileDaoImpl implements FileDao {
	
	@Autowired
    private FileRepo fileRepo;

	@Override
    public File create(File file) {
        return fileRepo.save(file);  
    }

    @Override
    public File findById(Long id) {
        return fileRepo.findById(id).orElse(null);  
    }
    
    @Override
    public File findByFileName(String fileName) {
        return fileRepo.findByFileName(fileName);
    }

    @Override
    public File update(File file) {
        return fileRepo.save(file);  
    }

    @Override
    public void delete(Long id) {
        fileRepo.deleteById(id);  
    }
}

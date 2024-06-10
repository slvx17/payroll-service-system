package com.lawencon.pss_app.dao;

import com.lawencon.pss_app.model.File;

public interface FileDao {

	File create(File file);

	File findById(Long id);

	File update(File file);

	void delete(Long id);

	File findByFileName(String fileName);

}

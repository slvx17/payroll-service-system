package com.lawencon.pss_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.pss_app.model.File;

@Repository
public interface FileRepo extends JpaRepository<File, Long> {

	File findByFileName(String name);

}

package com.lawencon.pss_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.pss_app.model.DeadlineType;

@Repository
public interface DeadlineTypeRepo extends JpaRepository<DeadlineType, Integer> {

}

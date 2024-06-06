package com.lawencon.pss_app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lawencon.pss_app.model.ChangeRequest;

@Repository
public interface ChangeRequestRepo extends JpaRepository<ChangeRequest, Long> {
    List<ChangeRequest> findByRequestStatus_Id(@Param("statusId") Integer statusId);
}

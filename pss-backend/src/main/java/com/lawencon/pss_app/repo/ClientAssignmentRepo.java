package com.lawencon.pss_app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.pss_app.model.ClientAssignment;
import com.lawencon.pss_app.model.User;

@Repository
public interface ClientAssignmentRepo extends JpaRepository<ClientAssignment, Long> {
	ClientAssignment findByClient(User user);
	List<ClientAssignment> findByPs(User user);
}

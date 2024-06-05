package com.lawencon.pss_app.dao;

import java.util.List;

import com.lawencon.pss_app.model.ClientAssignment;
import com.lawencon.pss_app.model.User;

public interface ClientAssignmentDao {
	ClientAssignment getByClient(User user); 
	ClientAssignment create(ClientAssignment ClientAssignment);
	ClientAssignment findById(Long id);
	List<ClientAssignment> findAll();
	ClientAssignment update(ClientAssignment ClientAssignment);
	void delete(ClientAssignment ClientAssignment);
}

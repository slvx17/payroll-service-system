package com.lawencon.pss_app.dao;

import java.util.List;

import com.lawencon.pss_app.model.ClientAssignment;

public interface ClientAssignmentDao {

	ClientAssignment create(ClientAssignment ClientAssignment);
	ClientAssignment findById(Long id);
	List<ClientAssignment> findAll();
	ClientAssignment update(ClientAssignment ClientAssignment);
	void delete(ClientAssignment ClientAssignment);
}

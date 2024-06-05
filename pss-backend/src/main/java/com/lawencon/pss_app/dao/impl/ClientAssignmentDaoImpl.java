package com.lawencon.pss_app.dao.impl;

import org.springframework.stereotype.Repository;

import com.lawencon.pss_app.dao.ClientAssignmentDao;
import com.lawencon.pss_app.model.ClientAssignment;
import com.lawencon.pss_app.model.User;
import com.lawencon.pss_app.repo.ClientAssignmentRepo;

@Repository
public class ClientAssignmentDaoImpl implements ClientAssignmentDao {
	private final ClientAssignmentRepo clientAssignmentRepo;
	
	public ClientAssignmentDaoImpl (ClientAssignmentRepo clientAssignmentRepo) {
		this.clientAssignmentRepo = clientAssignmentRepo;
	}
	
	@Override
	public ClientAssignment getByClient(User user) {
		return clientAssignmentRepo.findByClient(user);
	}

}

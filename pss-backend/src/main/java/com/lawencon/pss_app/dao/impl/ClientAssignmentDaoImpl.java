package com.lawencon.pss_app.dao.impl;
import java.util.List;

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
	public ClientAssignment findByClient(User user) {
		return clientAssignmentRepo.findByClient(user);
	}
	@Override
	public List<ClientAssignment> findByPs(User user) {
		return clientAssignmentRepo.findByPs(user);
	}
	@Override
	public ClientAssignment create(ClientAssignment clientAssignment) {
		return clientAssignmentRepo.save(clientAssignment);
	}

	@Override
	public ClientAssignment findById(Long id) {
		return clientAssignmentRepo.findById(id).orElse(null);
	}

	@Override
	public List<ClientAssignment> findAll() {
		return clientAssignmentRepo.findAll();
	}

	@Override
	public ClientAssignment update(ClientAssignment clientAssignment) {
		if (clientAssignment == null || clientAssignment.getId() == 0) {
            throw new IllegalArgumentException("ID cannot be null for update.");
        }
		
		return clientAssignmentRepo.save(clientAssignment);
	}

	@Override
	public void delete(ClientAssignment clientAssignment) {
		if (clientAssignment == null || clientAssignment.getId() == 0) {
            throw new IllegalArgumentException("ID cannot be null for delete.");
        }
		
		clientAssignmentRepo.deleteById(clientAssignment.getId());
	}

}

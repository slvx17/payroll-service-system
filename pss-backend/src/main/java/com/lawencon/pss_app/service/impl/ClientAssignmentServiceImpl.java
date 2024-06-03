package com.lawencon.pss_app.service.impl;

import org.springframework.stereotype.Service;

import com.lawencon.pss_app.dao.ClientAssignmentDao;
import com.lawencon.pss_app.dao.UserDao;
import com.lawencon.pss_app.dto.clientassignment.AssignUserResDto;
import com.lawencon.pss_app.model.ClientAssignment;
import com.lawencon.pss_app.service.ClientAssignmentService;

@Service
public class ClientAssignmentServiceImpl implements ClientAssignmentService {

	private ClientAssignmentDao clientAssignmentDao;
	private UserDao userDao;
	
	public ClientAssignmentServiceImpl(ClientAssignmentDao clientAssignmentDao, UserDao userDao) {
		this.clientAssignmentDao = clientAssignmentDao;
		this.userDao = userDao;
	}


	@Override
	public AssignUserResDto createAssignment(Long clId, Long psId) {
		ClientAssignment ca = new ClientAssignment();
		
		ca.setClient(userDao.findById(clId));
		ca.setPs(userDao.findById(psId));
		
		ClientAssignment newCa =  clientAssignmentDao.create(ca);
		
		return new AssignUserResDto(newCa.getId(), "Successfully created assignment!");
	}

}

package com.lawencon.pss_app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.pss_app.dao.ClientAssignmentDao;
import com.lawencon.pss_app.dao.UserDao;
import com.lawencon.pss_app.dto.clientassignment.AssignUserResDto;
import com.lawencon.pss_app.model.ClientAssignment;
import com.lawencon.pss_app.model.User;
import com.lawencon.pss_app.service.ClientAssignmentService;

@Service
public class ClientAssignmentServiceImpl implements ClientAssignmentService {

	private ClientAssignmentDao clientAssignmentDao;
	private UserDao userDao;
	
	public ClientAssignmentServiceImpl(ClientAssignmentDao clientAssignmentDao, UserDao userDao) {
		this.clientAssignmentDao = clientAssignmentDao;
		this.userDao = userDao;
	}


	@Transactional
	@Override
	public AssignUserResDto createAssignment(Long clId, Long psId) {
		ClientAssignment ca = new ClientAssignment();
		
		ca.setClient(userDao.findById(clId));
		ca.setPs(userDao.findById(psId));
		
		ClientAssignment newCa =  clientAssignmentDao.create(ca);
		
		return new AssignUserResDto(newCa.getId(), "Successfully created assignment!");
	}
	
	@Override
	public ClientAssignment getByClient(User user) {
		ClientAssignment res = clientAssignmentDao.findByClient(user);
		return res;
	}
	
	@Override
	public List<ClientAssignment> getByPs(User user) {
		List<ClientAssignment> res = clientAssignmentDao.findByPs(user);
		return res;

}

package com.lawencon.pss_app.service;

import java.util.List;

import com.lawencon.pss_app.dto.clientassignment.AssignUserResDto;
import com.lawencon.pss_app.model.ClientAssignment;
import com.lawencon.pss_app.model.User;

public interface ClientAssignmentService {
	AssignUserResDto createAssignment(Long clId, Long psId);

	ClientAssignment getByClient(User user);
	List<ClientAssignment> getByPs(User user);
}
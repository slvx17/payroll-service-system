package com.lawencon.pss_app.service;

import com.lawencon.pss_app.dto.clientassignment.AssignUserResDto;

public interface ClientAssignmentService {
	AssignUserResDto createAssignment(Long clId, Long psId);
}

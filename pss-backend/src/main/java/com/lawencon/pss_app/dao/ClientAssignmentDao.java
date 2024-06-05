package com.lawencon.pss_app.dao;

import com.lawencon.pss_app.model.ClientAssignment;
import com.lawencon.pss_app.model.User;

public interface ClientAssignmentDao {
	ClientAssignment getByClient(User user); 
}

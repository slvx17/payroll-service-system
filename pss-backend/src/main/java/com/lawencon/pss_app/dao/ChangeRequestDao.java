package com.lawencon.pss_app.dao;

import java.util.List;

import com.lawencon.pss_app.model.ChangeRequest;
import com.lawencon.pss_app.model.RequestStatusType;

public interface ChangeRequestDao {

	ChangeRequest create(ChangeRequest changeRequest);

	ChangeRequest update(ChangeRequest changeRequest);

	ChangeRequest findById(Long id);

	List<ChangeRequest> findByRequestStatus(RequestStatusType requestStatus);

	void delete(Long id);

}

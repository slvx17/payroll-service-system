package com.lawencon.pss_app.dao;

import com.lawencon.pss_app.model.RequestStatusType;

public interface RequestStatusTypeDao {

	RequestStatusType create(RequestStatusType requestStatusType);

	RequestStatusType findById(Integer id);

	RequestStatusType update(RequestStatusType requestStatusType);

	void delete(Integer id);

}

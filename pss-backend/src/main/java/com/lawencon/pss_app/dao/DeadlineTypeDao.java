package com.lawencon.pss_app.dao;

import com.lawencon.pss_app.model.DeadlineType;

public interface DeadlineTypeDao {

	DeadlineType create(DeadlineType deadlineType);

	DeadlineType findById(Integer id);

	void delete(Integer id);

}

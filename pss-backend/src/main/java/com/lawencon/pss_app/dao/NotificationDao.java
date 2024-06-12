package com.lawencon.pss_app.dao;

import java.util.List;

import com.lawencon.pss_app.model.Notification;
import com.lawencon.pss_app.model.User;

public interface NotificationDao {

	Notification create(Notification Notification);
	
	Notification update(Notification Notification);

	List<Notification> findByReceiver(User user); // get all
	
	Notification findById(Long id);
	
	void delete(Long id);
	
	void deleteByUser(User user);

}

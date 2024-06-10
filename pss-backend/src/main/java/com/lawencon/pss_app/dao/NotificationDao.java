package com.lawencon.pss_app.dao;

public interface NotificationDao {

	Notification create(Notification Notification);
	
	Notification update(Notification Notification);

	List<Notification> findByReceiver(User user); // get all
	
	Notification findById(Long id);
	
	void delete(Long id);

}

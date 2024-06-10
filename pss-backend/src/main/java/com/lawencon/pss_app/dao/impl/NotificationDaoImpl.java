package com.lawencon.pss_app.dao.impl;

import java.util.List;

import com.lawencon.pss_app.dao.NotificationDao;
import com.lawencon.pss_app.model.Notification;
import com.lawencon.pss_app.model.User;
import com.lawencon.pss_app.repo.NotificationRepo;

public class NotificationDaoImpl implements NotificationDao{
	private final NotificationRepo notificationRepo;

	public NotificationDaoImpl(NotificationRepo notificationRepo) {
		this.notificationRepo = notificationRepo;
	}
	
	@Override
	public Notification create(Notification notification) {
		return notificationRepo.save(notification);
	}

	@Override
	public Notification update(Notification notification) {
		return notificationRepo.save(notification);
	}

	@Override
	public List<Notification> findByReceiver(User user) {
		return notificationRepo.findByReceiver(user);
	}

	@Override
	public Notification findById(Long id) {
		return notificationRepo.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		notificationRepo.deleteById(id);
	}
}

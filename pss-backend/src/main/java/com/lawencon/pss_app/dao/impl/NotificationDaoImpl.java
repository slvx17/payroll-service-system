package com.lawencon.pss_app.dao.impl;

import com.lawencon.pss_app.dao.NotificationDao;

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

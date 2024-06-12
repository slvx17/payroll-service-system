package com.lawencon.pss_app.service;

import java.util.List;

import com.lawencon.pss_app.dto.notification.NotificationResDto;
import com.lawencon.pss_app.model.Notification;
import com.lawencon.pss_app.model.User;

public interface NotificationService {
	List<NotificationResDto> getByReceiver(User user);
	
	NotificationResDto getById(Long id);
	
	Notification create(Notification notification);
	
	Notification update(Notification notification);
	
	void delete(Long id);
}

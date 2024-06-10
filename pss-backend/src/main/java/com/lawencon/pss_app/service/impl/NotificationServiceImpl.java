package com.lawencon.pss_app.service.impl;

import java.util.ArrayList;
import java.util.List;

//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.lawencon.pss_app.dao.NotificationDao;
import com.lawencon.pss_app.dao.UserDao;
import com.lawencon.pss_app.dto.notification.NotificationResDto;
import com.lawencon.pss_app.model.Notification;
import com.lawencon.pss_app.model.User;
import com.lawencon.pss_app.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {
	private UserDao userDAO;
	private NotificationDao notificationDAO;
//    private JavaMailSender emailSender;
//    private JavaMailSenderImpl javamailsenderimpl;

	
	public NotificationServiceImpl(NotificationDao notificationDAO,
			UserDao userDAO) {
		this.notificationDAO = notificationDAO;
		this.userDAO = userDAO;
	}

	@Override
	public List<NotificationResDto> getByReceiver(User user) {
		List<Notification> notifications = notificationDAO.findByReceiver(user);
		List<NotificationResDto> result = new ArrayList<>();
		for(Notification notification : notifications) {
			NotificationResDto dto = new NotificationResDto();
            dto.setId(notification.getId());
            dto.setMessage(notification.getMessage());
            dto.setDateTimeSent(notification.getSendAt().toLocalDateTime());
            dto.setReceiverName(notification.getReceiver().getUsername());
            result.add(dto);
		}
		return result;
	}

	@Override
	public NotificationResDto getById(Long id) {
		Notification notification = notificationDAO.findById(id);
		NotificationResDto result = null;
		result.setId(notification.getId());
        result.setMessage(notification.getMessage());
        if(notification.getSendAt() != null)       result.setDateTimeSent(notification.getSendAt().toLocalDateTime());
        else result.setDateTimeSent(null);
//        result.setReceiverName(notification.getReceiver().getUsername());
		return result;
	}

	@Override
	public Notification create(Notification notification) {
//		SimpleMailMessage message = new SimpleMailMessage(); 
//		message.setFrom("noreply@baeldung.com");
//        message.setTo("arthur.l.wigo@gmail.com"); 
//        message.setSubject("Testing"); 
//        message.setText("Hello this is testing");
//        emailSender.send(message);
		return notificationDAO.create(notification);
	}

	@Override
	public Notification update(Notification notification) {
		return notificationDAO.update(notification);
	}

	@Override
	public void delete(Long id) {
		notificationDAO.delete(id);
	}

}

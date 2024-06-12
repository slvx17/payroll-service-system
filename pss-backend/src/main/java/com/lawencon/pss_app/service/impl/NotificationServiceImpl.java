package com.lawencon.pss_app.service.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.lawencon.pss_app.dao.ClientAssignmentDao;
import com.lawencon.pss_app.dao.DateDao;
import com.lawencon.pss_app.dao.NotificationDao;
import com.lawencon.pss_app.dao.ScheduleDao;
import com.lawencon.pss_app.dao.UserDao;
import com.lawencon.pss_app.dto.notification.NotificationResDto;
import com.lawencon.pss_app.model.ClientAssignment;
import com.lawencon.pss_app.model.Date;
import com.lawencon.pss_app.model.Notification;
import com.lawencon.pss_app.model.Schedule;
import com.lawencon.pss_app.model.User;
import com.lawencon.pss_app.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {
	private UserDao userDAO;
	private NotificationDao notificationDAO;
	private ClientAssignmentDao clientAssignmentDAO;
	private ScheduleDao scheduleDAO;
	private DateDao dateDAO;
    private JavaMailSender emailSender;

	
	public NotificationServiceImpl(NotificationDao notificationDAO,
			UserDao userDAO,
			ClientAssignmentDao clientAssignmentDAO,
			ScheduleDao scheduleDAO,
			DateDao dateDAO,
			JavaMailSender emailSender) {
		this.notificationDAO = notificationDAO;
		this.userDAO = userDAO;
		this.clientAssignmentDAO = clientAssignmentDAO;
		this.scheduleDAO = scheduleDAO;
		this.dateDAO = dateDAO;
		this.emailSender = emailSender;
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
	public Notification create(Notification notification){
//		MimeMessage message1 = emailSender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(message1, true);
//		helper.setFrom("noreply@baeldung.com");
//		helper.setTo("arthur.l.wigo@gmail.com"); 
//		helper.setSubject("Testing"); 
//		helper.setText("Hello this is testing");
//        emailSender.send(message1);
//		
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

	@Transactional
	@Override
	public void delete(String email) {
		User user = userDAO.findByEmail(email);
		notificationDAO.deleteByUser(user);
	}

	@Override
	public List<NotificationResDto> checkDeadlines(String email) {
		List<Notification> notifications = new ArrayList<>();
		User user = userDAO.findByEmail(email);
		ClientAssignment clientAssignment = clientAssignmentDAO.findByClient(user);	
        List<Schedule> schedule = scheduleDAO.findByClientAssignment(clientAssignment);
        for (Schedule eachschedule : schedule) {
            List<Date> dates = dateDAO.findBySchedule(eachschedule); // This for loop is kinda unnecessary (check if month is date)
            
            for(Date date : dates) {
            	if (LocalDate.now() == date.getDeadlineDate().minusDays(2)) {
            		Notification notification = new Notification();
                    notification.setMessage(date.getDeadlineType().getDeadlineName() + " deadline due in 2 days");
                    notification.setSendAt(Timestamp.valueOf(LocalDateTime.now()));   
                    notification.setReceiver(user);
                    notification.setSender(null);
                    notification.setIsRead(false);
                    notificationDAO.create(notification);
                    notifications.add(notification);
            	}
            }
        }
        
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

}

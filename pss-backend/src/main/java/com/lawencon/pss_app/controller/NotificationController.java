package com.lawencon.pss_app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.pss_app.dto.email.EmailReqDto;
import com.lawencon.pss_app.dto.notification.NotificationResDto;
import com.lawencon.pss_app.model.Notification;
import com.lawencon.pss_app.model.User;
import com.lawencon.pss_app.service.NotificationService;
import com.lawencon.pss_app.service.UserService;

@RestController
@RequestMapping("/notification")
public class NotificationController {
	
	private NotificationService notificationService;
	private UserService userService;
	
	public NotificationController(NotificationService notificationService,
			UserService userService) {
		this.notificationService = notificationService; 
		this.userService = userService;
	}
	
	@PostMapping("/getall")
	public ResponseEntity<List<NotificationResDto>> getAllNotifications(@RequestBody EmailReqDto emailReq){
		List<NotificationResDto> result = notificationService.getByReceiver(userService.getByEmail(emailReq.getEmail()));
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Notification> createNotification(@RequestBody Notification notification){
		Notification result = notificationService.create(notification);
		return ResponseEntity.ok(result);
	}
}

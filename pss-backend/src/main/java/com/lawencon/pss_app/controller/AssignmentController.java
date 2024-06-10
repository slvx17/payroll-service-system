package com.lawencon.pss_app.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.pss_app.dto.clientassignment.AssignUserResDto;
import com.lawencon.pss_app.dto.clientassignment.CreateAssignmentReqDto;
import com.lawencon.pss_app.dto.email.EmailReqDto;
import com.lawencon.pss_app.dto.user.UserResDto;
import com.lawencon.pss_app.model.ClientAssignment;
import com.lawencon.pss_app.model.Notification;
import com.lawencon.pss_app.model.User;
import com.lawencon.pss_app.service.ClientAssignmentService;
import com.lawencon.pss_app.service.NotificationService;
import com.lawencon.pss_app.service.UserService;

@RestController
@RequestMapping("assignuser")
public class AssignmentController {
	
	private final UserService userService;
	private final ClientAssignmentService clientAssignmentService;
	private NotificationService notificationService;
	

	public AssignmentController(UserService userService, 
			ClientAssignmentService clientAssignmentService,
			NotificationService notificationService) {
		this.userService = userService;
		this.clientAssignmentService = clientAssignmentService;
		this.notificationService = notificationService;
	}

	@GetMapping("/getallps")
    public ResponseEntity<List<User>> getAllPs() {
        List<User> psUsers = userService.getAllPs();
        return ResponseEntity.ok(psUsers); 
    }

	@PostMapping("/getallcbyps")
	public ResponseEntity<List<User>> getAllCByPs(@RequestBody EmailReqDto emailReq) {
		User user = userService.getByEmail(emailReq.getEmail());
		List<User> cUsers = new ArrayList<>();
		List<ClientAssignment> clientAssignment = clientAssignmentService.getByPs(user);
		for (ClientAssignment eachclientAssignment : clientAssignment) {
			cUsers.add(eachclientAssignment.getClient());
		}
		return ResponseEntity.ok(cUsers);
	}
	
	@GetMapping("/getclientbyemail")
	public ResponseEntity<UserResDto> getClientByEmail(@RequestParam String email) {
	    User client = userService.getClientByEmail(email);
	    UserResDto res;
	    if (client != null) {
	        if (clientAssignmentService.getByClient(client) != null) {
	            res = new UserResDto(client.getId(), client.getUsername(), client.getEmail(), "This client is already assigned");
	        } else {
	            res = new UserResDto(client.getId(), client.getUsername(), client.getEmail(), "Success");
	        }
	    } else {
	        res = new UserResDto(null, null, null, "User with this email not found");
	    }
	    
	    return ResponseEntity.ok(res);
	}
	
	@PostMapping("/create")
    public ResponseEntity<AssignUserResDto> createAssignment(@RequestBody CreateAssignmentReqDto req) {
        Notification notificationCL = new Notification();
        notificationCL.setMessage("You have been assigned to " + userService.getById(req.getPayrollServiceId()).getUsername());
        notificationCL.setSendAt(Timestamp.valueOf(LocalDateTime.now()));   
        notificationCL.setReceiver(userService.getById(req.getClientId()));
        notificationCL.setSender(null);
        notificationCL.setIsRead(false);
        notificationService.create(notificationCL);
        
        Notification notificationPS = new Notification();
        notificationPS.setMessage("You have a new client: " + userService.getById(req.getClientId()).getUsername());
        notificationPS.setSendAt(Timestamp.valueOf(LocalDateTime.now()));   
        notificationPS.setReceiver(userService.getById(req.getPayrollServiceId()));
        notificationPS.setSender(null);
        notificationPS.setIsRead(false);
        notificationService.create(notificationPS);
        
		AssignUserResDto responseDto = clientAssignmentService.createAssignment(req.getClientId(), req.getPayrollServiceId());
        return ResponseEntity.ok(responseDto);
    }
}

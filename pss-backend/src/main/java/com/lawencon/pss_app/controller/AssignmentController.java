package com.lawencon.pss_app.controller;

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
import com.lawencon.pss_app.dto.user.UserResDto;
import com.lawencon.pss_app.model.User;
import com.lawencon.pss_app.service.ClientAssignmentService;
import com.lawencon.pss_app.service.UserService;

@RestController
@RequestMapping("assignuser")
public class AssignmentController {
	
	private final UserService userService;
	private final ClientAssignmentService clientAssignmentService;
	

	public AssignmentController(UserService userService, ClientAssignmentService clientAssignmentService) {
		this.userService = userService;
		this.clientAssignmentService = clientAssignmentService;
	}

	@GetMapping("/getallps")
    public ResponseEntity<List<User>> getAllPs() {
        List<User> psUsers = userService.getAllPs();
        return ResponseEntity.ok(psUsers); 
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
		AssignUserResDto responseDto = clientAssignmentService.createAssignment(req.getClientId(), req.getPayrollServiceId());
        return ResponseEntity.ok(responseDto);
    }
}
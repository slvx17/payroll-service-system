package com.lawencon.pss_app.dto.clientassignment;

public class AssignUserResDto {
	private Long assignmentId;
	private String message;
	
	public AssignUserResDto(Long assignmentId, String message) {
		this.assignmentId = assignmentId;
		this.message = message;
	}
	
	public Long getAssignmentId() {
		return assignmentId;
	}
	public void setAssignmentId(Long assignmentId) {
		this.assignmentId = assignmentId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}

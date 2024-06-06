package com.lawencon.pss_app.dto.user;

public class UserResDto {
	private Long id;
	private String username;
    private String message;
    
	public UserResDto(Long id, String username, String message) {
		this.id = id;
		this.username = username;
		this.message = message;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getmessage() {
		return message;
	}
	public void setmessage(String message) {
		this.message = message;
	}
    
    
}
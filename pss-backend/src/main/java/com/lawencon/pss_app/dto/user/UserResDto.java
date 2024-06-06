package com.lawencon.pss_app.dto.user;

public class UserResDto {
	private Long id;
	private String username;
	private String email;
    private String message;
    
	public UserResDto(Long id, String username, String email, String message) {
		this.id = id;
		this.username = username;
		this.message = message;
		this.email = email;
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
	
	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    
    
}
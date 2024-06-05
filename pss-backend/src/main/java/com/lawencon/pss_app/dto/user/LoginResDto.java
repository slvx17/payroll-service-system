package com.lawencon.pss_app.dto.user;

public class LoginResDto {

	private String role;
	private String token;
	private String email;

	public LoginResDto() {
		
	}
	
	public LoginResDto(String string, String email) {
		this.token = string;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}

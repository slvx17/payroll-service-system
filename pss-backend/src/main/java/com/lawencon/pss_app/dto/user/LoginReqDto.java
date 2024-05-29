package com.lawencon.pss_app.dto.user;

public class LoginReqDto {
	private String email;
	private String password;
	
	public LoginReqDto() {
		
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

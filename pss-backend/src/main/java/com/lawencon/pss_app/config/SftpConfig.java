package com.lawencon.pss_app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SftpConfig {
	
	@Value("${sftp.host}")
	private String host;
	
	@Value("${sftp.port}")
	private Integer port;
	
	@Value("${sftp.user}")
	private String user;
	
	@Value("${sftp.password}")
	private String password;
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

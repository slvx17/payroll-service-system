package com.lawencon.pss_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.lawencon.pss_app.dto.user.LoginReqDto;
import com.lawencon.pss_app.dto.user.LoginResDto;
import com.lawencon.pss_app.dto.user.UserRegisReqDto;
import com.lawencon.pss_app.dto.user.UserRegisResDto;
import com.lawencon.pss_app.model.User;

public interface UserService extends UserDetailsService {

	Optional<User> getByEmail(String email);
	List<User> getAllPs();
	User getClientByEmail(String email);
	UserRegisResDto createUser(UserRegisReqDto reqDto);
	LoginResDto authenticateUser(LoginReqDto loginReqDto);
}
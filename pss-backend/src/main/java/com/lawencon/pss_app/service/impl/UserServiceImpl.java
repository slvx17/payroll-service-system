package com.lawencon.pss_app.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.pss_app.constant.RoleEnum;
import com.lawencon.pss_app.dao.UserDao;
import com.lawencon.pss_app.dto.user.LoginReqDto;
import com.lawencon.pss_app.dto.user.LoginResDto;
import com.lawencon.pss_app.dto.user.UserRegisReqDto;
import com.lawencon.pss_app.dto.user.UserRegisResDto;
import com.lawencon.pss_app.model.Role;
import com.lawencon.pss_app.model.User;
import com.lawencon.pss_app.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDao userDAO;
    private PasswordEncoder encoder;

    public UserServiceImpl(UserDao userDAO
    		, PasswordEncoder encoder
    		){
    	this.userDAO = userDAO;
    	this.encoder = encoder;
    }

    @Override 
    public User getById(Long id) {
    	return userDAO.findById(id);
    }
    
    @Override
	public Optional<User> getByEmail(String email){
        User user = userDAO.findByEmail(email);
        return Optional.ofNullable(user);
    }
    
    @Override
	public List<User> getAllPs() {
		List<User> psUsers = userDAO.findByRole(new Role(RoleEnum.PAYROLL_SERVICE));
		return psUsers;
	}


	@Override
	public User getClientByEmail(String email) {
		User client = userDAO.findByEmailAndRole(email, new Role(RoleEnum.CLIENT));
		return client;
	}
    
    @Transactional
    @Override
    public UserRegisResDto createUser(UserRegisReqDto reqDto) {
        User newUser = new User();
        newUser.setUsername(reqDto.getUserName());
        newUser.setEmail(reqDto.getUserEmail());
        newUser.setUserPassword(encoder.encode(reqDto.getPassword()));

        Role role = new Role(RoleEnum.getById(reqDto.getRoleId()));  
        newUser.setRole(role);

        User createdBy = userDAO.findById(reqDto.getCreatedById());  
        newUser.setCreatedBy(createdBy);

        Timestamp curr = new Timestamp(System.currentTimeMillis());
        LocalDateTime localDateTime = curr.toLocalDateTime();
        newUser.setCreatedAt(localDateTime);
        
        newUser.setUserVersion(1);
        newUser.setIsActive(true);
        
        User createdUser = userDAO.create(newUser);

        return new UserRegisResDto(createdUser.getId(),"User created successfully!");
    }
    
    @Override
    public LoginResDto authenticateUser(LoginReqDto loginReqDto)  {
    	User user = userDAO.findByEmail(loginReqDto.getEmail());
    	final var loginRes = new LoginResDto();
		if (user!=null) {
			System.out.println(loginReqDto.getEmail() + " is logged in");
		} else {
			System.out.println("User not found.");
		}

		return loginRes; 
    }


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDAO.findByEmail(email);
		return new org.springframework.security.core.userdetails.User(email, user.getUserPassword(), new ArrayList<>());
	}
}
package com.lawencon.pss_app.dao;

import java.util.List;

import com.lawencon.pss_app.model.Role;
import com.lawencon.pss_app.model.User;

public interface UserDao {

	User create(User user);
	User findById(Long id);
	User findByEmail(String email);
	List<User> findAll();
	List<User> findByRole(Role role);
	User update(User user);
	void delete(User user);
}

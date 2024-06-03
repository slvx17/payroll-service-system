package com.lawencon.pss_app.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.pss_app.dao.UserDao;
import com.lawencon.pss_app.model.Role;
import com.lawencon.pss_app.model.User;
import com.lawencon.pss_app.repo.UserRepo;

@Repository
public class UserDaoImpl implements UserDao {

	private final UserRepo userRepo;
	
	public UserDaoImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	@Override
	public User create(User user) {
		return userRepo.save(user);
	}
	
	@Override
	public User findById(Long id) {
		return userRepo.findById(id).orElse(null);
	}

	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}

	@Override
	public List<User> findByRole(Role role) {
		return userRepo.findByRole(role);
	}
	
	@Override
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	@Override
	public User findByEmailAndRole(String email, Role role) {
		return userRepo.findByEmailAndRole(email, role);
	}

	@Override
	public User update(User user) {
		if (user == null || user.getId() == 0) {
            throw new IllegalArgumentException("ID cannot be null for update.");
        }
		
		return userRepo.save(user);
	}

	@Override
	public void delete(User user) {
		if (user == null || user.getId() == 0) {
            throw new IllegalArgumentException("ID cannot be null for deletion.");
        }
		
		userRepo.deleteById(user.getId());
	}
	
}

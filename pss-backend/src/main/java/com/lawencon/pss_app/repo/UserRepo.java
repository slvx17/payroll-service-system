package com.lawencon.pss_app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.pss_app.model.Role;
import com.lawencon.pss_app.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
	List<User> findByRole(Role role);
	User findByEmail(String email);
	User findByEmailAndRole(String email, Role role);
}

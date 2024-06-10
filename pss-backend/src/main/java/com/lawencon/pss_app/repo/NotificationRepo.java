package com.lawencon.pss_app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.pss_app.model.Notification;
import com.lawencon.pss_app.model.User;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long> {
	List<Notification> findByReceiver(User user);
}

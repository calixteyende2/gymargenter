package com.gymargente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gymargente.entities.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}

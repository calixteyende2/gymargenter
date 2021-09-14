package com.sygescom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sygescom.entities.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}

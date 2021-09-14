package com.gymargente.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gymargente.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Page<User> findByUsernameContains(String username, Pageable pageable);
}

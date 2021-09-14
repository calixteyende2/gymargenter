package com.gymargente.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gymargente.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Page<Role> findByRoleNameContains(String role, Pageable pageable);
}

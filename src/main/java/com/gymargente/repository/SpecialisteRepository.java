package com.gymargente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gymargente.entities.Specialiste;


public interface SpecialisteRepository extends JpaRepository<Specialiste, Long> {

	Specialiste findByEmail(String email);	
	
}

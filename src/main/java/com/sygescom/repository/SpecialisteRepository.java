package com.sygescom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sygescom.entities.Specialiste;


public interface SpecialisteRepository extends JpaRepository<Specialiste, Long> {

	Specialiste findByEmail(String email);	
	
}

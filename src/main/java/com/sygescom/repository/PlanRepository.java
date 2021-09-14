package com.sygescom.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sygescom.entities.Plan;

public interface PlanRepository extends  JpaRepository <Plan, Long> {
	
	 Page<Plan>findByNameContains(String mc, Pageable pageable);  

}

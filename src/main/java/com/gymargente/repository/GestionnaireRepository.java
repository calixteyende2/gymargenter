package com.gymargente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gymargente.entities.Gestionnaire;

public interface GestionnaireRepository extends JpaRepository<Gestionnaire, Long> {

	
}

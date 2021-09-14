package com.gymargente.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.gymargente.entities.Client;


public interface ClientRepository extends JpaRepository<Client, Long> {
	
	Client findByEmail(String email);
	
}

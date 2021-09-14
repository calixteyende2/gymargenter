package com.sygescom.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sygescom.entities.Client;


public interface ClientRepository extends JpaRepository<Client, Long> {
	
	Client findByEmail(String email);
	
}

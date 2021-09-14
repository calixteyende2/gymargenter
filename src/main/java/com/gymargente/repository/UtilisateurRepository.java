package com.gymargente.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gymargente.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

	public Page<Utilisateur> findByfirstnameContains(String mc, Pageable pageable);
	
	public Page<Utilisateur> findByEmailContains(String mc, Pageable pageable);
	
	public Utilisateur findByEmail(String mc);
	
	/*Client, Spécialiste ou Gestionnaire ou Administration*/ 
	@Query(value = "select * from utilisateur u where u.status_name =:status", nativeQuery = true)
	public Page<Utilisateur> searchAll(@Param("status")String status, Pageable pageable);
				
	
	/*Tous les spécialistes*/ 
	@Query(value = "select * from utilisateur u where lower(u.status_name) = lower(:status) and lower(u.ENTP) = lower(:ENTP)  or lower(u.status_name) = lower(:status) and lower(u.NUTRI) = lower(:NUTRI) or lower(u.status_name) = lower(:status) and lower(u.PHYSIO) = lower(:PHYSIO) ", nativeQuery = true)
	public Page<Utilisateur> findByStatusAndENTPContainsOrNUTRIContainsOrPHYSIOContains(@Param("status")String status, @Param("ENTP")String mc1, @Param("NUTRI")String mc2, @Param("PHYSIO")String mc3, Pageable pageable);
	
	/*Un seul les spécialistes*/ 
	@Query(value = "select * from utilisateur u where lower(u.email) = lower(:email) and lower(u.status_name) = lower(:status)", nativeQuery = true)
	public Page<Utilisateur> findByEmailAndStatus(@Param("email")String email, @Param("status")String status,  Pageable pageable);
		
}

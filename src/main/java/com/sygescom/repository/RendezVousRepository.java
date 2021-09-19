package com.sygescom.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sygescom.entities.RendezVous;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
	
	 @Query(value = "select * from calandrier c where lower(c.responsable) = lower(:email) and  c.supprimer =:supprimer or lower(c.participant) like lower(concat('%', :email, '%')) and  c.supprimer =:supprimer", nativeQuery = true)	 
	 public Page<RendezVous>calandriersEnvoyesOuReçus(@Param("email")String email, @Param("supprimer")Boolean supprimer, Pageable pageable);

}

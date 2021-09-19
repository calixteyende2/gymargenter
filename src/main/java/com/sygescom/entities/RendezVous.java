package com.sygescom.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Validated
@Data @NoArgsConstructor @AllArgsConstructor
public class RendezVous implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

    private String specialiste; //Email du spécialiste
    
    @NotEmpty
    @NotNull
	private String domaine;		//Domaine de compétence
    
  
    @NotEmpty
    @NotNull
    private String client;		//Email du client
    
    private String description;	//Description de la rencontre 
    	
    
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
    @NotNull(message = "Date de debut ne peut etre null !!")
	private LocalDateTime dateDebut;
	
	
    
    @NotNull
	private int quantite;		//Nombre d'heure de la rencontre

}

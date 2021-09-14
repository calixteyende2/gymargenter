package com.sygescom.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; 


@Entity
@Validated
@Data @NoArgsConstructor @AllArgsConstructor
public class Utilisateur implements Serializable  {	

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotNull
	@NotBlank(message = "Prenom utilisateur ne peut �tre vide !!")
	@Size(min = 4,max = 10,message="Prenom doit avoir entre 4 et 10 characteres !!")
	private String firstname;
	
	@NotNull
	@NotBlank(message = "Nom utilisateur ne peut �tre vide !!")
	@Size(min = 4,max = 10,message="Nom doit avoir entre 4 et 10 characteres !!")
	private String lastname;
	
	@NotNull
	@NotBlank(message = "Telephone utilisateur ne peut etre vide !!")
	private String phone;
	
	
	@Column(nullable=false)
	@Email(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message="Entrez un email valid !!")
	private String email;
	 
	@NotNull
	@Size(min=4, max=255)
	private String password;
	
	@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "Date de naissance ne peut etre null !!")
	private Date datenaissance;	
	    
		 
}

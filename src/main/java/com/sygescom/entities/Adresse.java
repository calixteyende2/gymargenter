package com.sygescom.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Validated
@Data @NoArgsConstructor @AllArgsConstructor
public class Adresse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private int numeroRue; 
	
	@NotNull
	@NotBlank(message = "Nom rue ne peut �tre vide !!")
	private String nomRue;
	
	
	private int numeroApp;
	
	@NotNull
	private String ville;
	
	@NotNull
	@NotBlank(message = "Nom province ou region peut être vide !!")
	private String provinceRegion;
	
	@NotNull
	@NotBlank(message = "Nom pays ne peut être vide !!")
	private String pays;
	
	
	@OneToMany(cascade = CascadeType.MERGE)
	@JoinColumn(name= "adresse_id", nullable = false) 
	private List<Utilisateur>  utilisateurList = new ArrayList<Utilisateur>();
	
}

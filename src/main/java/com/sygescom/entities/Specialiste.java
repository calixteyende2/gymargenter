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

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Validated
@Data @NoArgsConstructor @AllArgsConstructor 
@EqualsAndHashCode(callSuper=true)
public class Specialiste extends Utilisateur implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	    
	
    /*
     * Services spécialisés 
     */  
    private String ENTP;  		//Entraineur privé
    
    private String NUTRI;  		//Nutritionniste
    
    private String PHYSIO;		//Physiologie
    
    
	@OneToMany(cascade = CascadeType.MERGE)
	@JoinColumn(name= "specialiste_id", referencedColumnName = "id")
    private List<Calandrier> calandriersList = new ArrayList<Calandrier>();

	
}

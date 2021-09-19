package com.sygescom.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Validated
@Data @NoArgsConstructor @AllArgsConstructor 
@EqualsAndHashCode(callSuper=true)
public class Client extends Utilisateur implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "TIMESTAMP")
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm") 
	@NotNull(message = "Date de adhésion ne peut etre null !!") 
	private LocalDateTime  dateDebut;
	
	
    @Column(columnDefinition = "TIMESTAMP")
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm") 
	@NotNull(message = "Date de Fin adhésion ne peut etre null !!") 
	private LocalDateTime dateFin;
    
    	 	
    
    /*
     * Plans d'abonnements 
     */
    private String CM;  	//Conditionnement mensuel
    
    private String CT;		//Conditionnement trimestriel
    
    private String CA;		//Conditionnement annuel
    
    private String OPTCGM;	//Option de conditionnement mensuel
    
    private String OPTCGT;	//Option de conditionnement trimestriel
    
    private String OPTCGA;	//Option de conditionnement annuel
    
    private String CGM;		//Cours de groupe mensuel
    
    private String CGT;		//Cours de groupe trimestriel
    
    private String CGA;		//Cours de groupe annuel
    

    /*
     * Services spécialisés 
     */
    
    private int qENTP; 	 //Entraineur particulier
    
   
    private int qNUTRI;  //Nutritionniste
    
   
    private int qPHYSIO; //Physiothérapuete
    
	
}

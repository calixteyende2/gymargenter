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
    private String CM;
    
    private String CT;
    
    private String CA;
    
    private String OPTCGM;
    
    private String OPTCGT;
    
    private String OPTCGA;
    
    private String CGM;
    
    private String CGT;
    
    private String CGA;
    

    /*
     * Services spécialisés 
     */
    private int qENTP;
    
    private int qNUTRI;
    
    private int qPHYSIO;
    
	
}

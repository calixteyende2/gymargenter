package com.gymargente.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Validated
@Component
@Data @NoArgsConstructor @AllArgsConstructor
public class Notification implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	@Email(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message="Entrez un email valid !!")
	private String emailGestionnaire;
	
	@Column(nullable=false)
	@Email(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message="Entrez un email valid !!")
	private String emailClient;

	@Column(nullable=false)
	private String message;
	
	@Column(columnDefinition = "TIMESTAMP")
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
	@NotNull(message = "Date de Fin adhésion ne peut etre null !!") 
	private LocalDateTime dateFin;
	

	
	
}

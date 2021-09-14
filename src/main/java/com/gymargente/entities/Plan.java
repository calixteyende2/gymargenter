package com.gymargente.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Validated
@Data @NoArgsConstructor @AllArgsConstructor
public class Plan implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private int prix;
	
	@NotNull
	@NotBlank(message = "nom plan ne peut �tre vide !!")
	private String name;
	
	@NotNull
	@Min(4)
	@NotBlank(message = "Code plan ne peut �tre vide !!")
	private String code;
	
}

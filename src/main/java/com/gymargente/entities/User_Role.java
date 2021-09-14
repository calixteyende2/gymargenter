package com.gymargente.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Validated
@Table(name = "user_role")
@Data @NoArgsConstructor
public class User_Role implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Id
	private String username;  
	
	@NotNull
	@NotBlank(message = "Nom role ne peut être vide !!")
	private String role;

	public User_Role(String username, @NotNull @NotBlank(message = "Nom role ne peut être vide !!") String role) {
		super();
		this.username = username;
		this.role = role;
	}
	
	

}

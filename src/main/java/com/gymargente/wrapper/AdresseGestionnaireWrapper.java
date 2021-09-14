package com.gymargente.wrapper;

import javax.validation.Valid;

import com.gymargente.entities.Adresse;
import com.gymargente.entities.Gestionnaire;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor @AllArgsConstructor
public class AdresseGestionnaireWrapper {	
	
	@Valid
	private Gestionnaire gestionnaire;
	
	@Valid
	private Adresse adresse;

}

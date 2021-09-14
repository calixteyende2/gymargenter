package com.sygescom.wrapper;

import javax.validation.Valid;

import com.sygescom.entities.Adresse;
import com.sygescom.entities.Gestionnaire;

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

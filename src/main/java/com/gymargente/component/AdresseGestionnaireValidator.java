package com.gymargente.component;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class AdresseGestionnaireValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
	    return AdresseGestionnaireValidator.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}
	

	
}

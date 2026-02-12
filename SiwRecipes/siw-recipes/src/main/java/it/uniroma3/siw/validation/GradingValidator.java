package it.uniroma3.siw.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GradingValidator implements ConstraintValidator<ValidGrading, Integer>{

	private static final int MIN_RATING = 1;
	private static final int MAX_RATING = 5;
	
	//Verifica della validità del voto
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if(value == null)
			return false;
		return value >= MIN_RATING && value <= MAX_RATING;
	}

}

package it.uniroma3.siw.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = GradingValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface ValidGrading {
	
	String message() default "Valutazione non valida";  //messaggio di errore se la validazione fallisce

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

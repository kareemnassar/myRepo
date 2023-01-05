package com.drones.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ FIELD })
@Retention(RUNTIME)

@Documented
@Constraint(validatedBy = { StateImpl.class })
public @interface State {
	String[] acceptedValues();

	String message() default "State can accept only one of these values (IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING).";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}

package com.drones.validator;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateImpl implements ConstraintValidator<State, String>{
	List<String> acceptedValues;
	@Override
    public void initialize(State constraintAnnotation) {
		acceptedValues = new ArrayList<String>();
        for(String val : constraintAnnotation.acceptedValues()) {
        	acceptedValues.add(val.toUpperCase());
        }
    }
	@Override 
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return acceptedValues.contains(value.toUpperCase());
	}
}

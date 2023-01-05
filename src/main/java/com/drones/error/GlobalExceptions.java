package com.drones.error;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;


@ControllerAdvice
public class GlobalExceptions extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<?> recordNotFoundException(IllegalStateException e){
		
		ErrorResponse error= new ErrorResponse(e.getMessage(),Arrays.asList(e.getMessage()));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e){
		List<String> errors=new ArrayList<String>();
		    for (ConstraintViolation cv : e.getConstraintViolations()) {
		    	errors.add( cv.getMessage());
		         
		    }
		ErrorResponse error= new ErrorResponse(errors.toString(),errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		List<String> errors=new ArrayList<String>();
		
		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			errors.add(fieldError.getDefaultMessage());
		}
		for (ObjectError objectError : ex.getBindingResult().getGlobalErrors()) {
			errors.add(objectError.getDefaultMessage());
		}
		ErrorResponse error= new ErrorResponse(ex.getMessage(), errors);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}

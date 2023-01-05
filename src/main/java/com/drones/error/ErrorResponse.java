package com.drones.error;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class ErrorResponse {

	private Boolean success;
	private String message;
	private LocalDateTime dateTime;
	private List<String> details;

	public ErrorResponse( String message,  List<String> details) {
		super();
		this.success = Boolean.FALSE;
		this.message = message;
		this.dateTime = LocalDateTime.now();
		this.details = details;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public List<String> getDetails() {
		return details;
	}
	public void setDetails(List<String> details) {
		this.details = details;
	}
	
	
	

}

package com.microservices.udemy.exception;

import java.time.LocalDateTime;

public class ErrorDetails {

	private LocalDateTime date;
	private String message;
	private String description;

	public ErrorDetails(LocalDateTime date, String message, String description) {
		super();
		this.date = date;
		this.message = message;
		this.description = description;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ErrorDetails [date=" + date + ", message=" + message + ", description=" + description + "]";
	}

}

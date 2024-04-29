package com.demo.helper;

import org.springframework.http.HttpStatus;



public class CustomResponse {

	private String message;
	private HttpStatus httpStatus;
	private boolean success = false;

	// Constructor
	public CustomResponse(String message, HttpStatus httpStatus, boolean success) {
		this.message = message;
		this.httpStatus = httpStatus;
		this.success = success;
	}

	// Getters and Setters
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	// Builder method
	public static CustomResponseBuilder builder() {
		return new CustomResponseBuilder();
	}

	// Builder class
	public static class CustomResponseBuilder {
		private String message;
		private HttpStatus httpStatus;
		private boolean success;

		public CustomResponseBuilder message(String message) {
			this.message = message;
			return this;
		}

		public CustomResponseBuilder httpStatus(HttpStatus httpStatus) {
			this.httpStatus = httpStatus;
			return this;
		}

		public CustomResponseBuilder success(boolean success) {
			this.success = success;
			return this;
		}

		public CustomResponse build() {
			return new CustomResponse(message, httpStatus, success);
		}
	}
}


package com.wf.training.sba3.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<CustomErrorDetails> handleUserNotFoundException(UserNotFoundException ex,
			WebRequest request) {
		List<String> message = new ArrayList<String>();
		message.add(ex.getMessage());
		CustomErrorDetails CustomErrorDetails = new CustomErrorDetails(new Date(), message,
				request.getDescription(false));
		return new ResponseEntity<>(CustomErrorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InterviewNotFoundException.class)
	public final ResponseEntity<CustomErrorDetails> handleUserNotFoundException(InterviewNotFoundException ex,
			WebRequest request) {
		List<String> message = new ArrayList<String>();
		message.add(ex.getMessage());
		CustomErrorDetails CustomErrorDetails = new CustomErrorDetails(new Date(), message,
				request.getDescription(false));
		return new ResponseEntity<>(CustomErrorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<CustomErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
		List<String> message = new ArrayList<String>();
		message.add(ex.getMessage());
		CustomErrorDetails CustomErrorDetails = new CustomErrorDetails(new Date(), message,
				request.getDescription(false));
		return new ResponseEntity<>(CustomErrorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> message = new ArrayList<String>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			message.add(error.getDefaultMessage());
		}
		CustomErrorDetails CustomErrorDetails = new CustomErrorDetails(new Date(), message,
				request.getDescription(false));
		return new ResponseEntity<>(CustomErrorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> message = new ArrayList<String>();
		message.add(ex.getMessage());
		CustomErrorDetails CustomErrorDetails = new CustomErrorDetails(new Date(), message,
				request.getDescription(false));
		return new ResponseEntity<>(CustomErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
	}

}

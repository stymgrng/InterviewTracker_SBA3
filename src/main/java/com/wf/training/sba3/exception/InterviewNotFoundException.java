package com.wf.training.sba3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InterviewNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InterviewNotFoundException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
}

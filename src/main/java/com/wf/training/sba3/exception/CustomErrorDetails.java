package com.wf.training.sba3.exception;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomErrorDetails {
	
	private Date timestamp;
	private List<String> message;
	private String requestUri;
		
}

package com.wf.training.sba3.dto;

import com.wf.training.sba3.entity.Interview;
import com.wf.training.sba3.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@NoArgsConstructor
//@AllArgsConstructor
@Data
public class AttendeeDTO {

	private Long attendeeId;
	private User user;
	private Interview interview;
	
}

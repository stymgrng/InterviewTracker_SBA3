package com.wf.training.sba3.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//@NoArgsConstructor
//@AllArgsConstructor
@Data
public class InterviewDTO {

	private Long interviewId;

	private String interviewerName;
	private String interviewName;
	private String userSkills;
	private LocalTime time;
	private LocalDate date;
	private String interviewStatus;
	private String remarks;

}

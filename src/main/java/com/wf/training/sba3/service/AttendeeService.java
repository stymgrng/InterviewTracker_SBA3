package com.wf.training.sba3.service;

import java.util.List;

import com.wf.training.sba3.dto.AttendeeDTO;

public interface AttendeeService {

	public AttendeeDTO addAttendee(Long interviewId, Long userId);

	public List<AttendeeDTO> getAllAttendees();

	public List<AttendeeDTO> findAttendeeByUserID(Long userId);

	public List<AttendeeDTO> findAttendeeByInterviewID(Long interviewId);

}

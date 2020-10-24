package com.wf.training.sba3.service;

import java.util.List;

import com.wf.training.sba3.dto.InterviewDTO;

public interface InterviewService {

	public InterviewDTO addNewInterview(InterviewDTO interviewDTO);

	public InterviewDTO deleteInterview(Long interviewId);

	public InterviewDTO updateInterviewStatus(Long interviewId, String status);

	public List<InterviewDTO> getInterviewByName(String interviewName);

	public List<InterviewDTO> getInterviewsByInterviewer(String interviewerName);

	public List<InterviewDTO> getInterviewByInterViewerandInterviewName(String interviewerName, String interviewName);

	public List<InterviewDTO> getAllInterviews();

	public InterviewDTO getInterviewWithId(long interviewId);

}

package com.wf.training.sba3.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wf.training.sba3.dto.InterviewDTO;
import com.wf.training.sba3.entity.Interview;
import com.wf.training.sba3.exception.InterviewNotFoundException;
import com.wf.training.sba3.repository.InterviewRepository;
import com.wf.training.sba3.service.InterviewService;

@Service
public class InterviewServiceImpl implements InterviewService {

	private static final String INTERVIEW_NOT_FOUND_STRING = "Interview with the following Name/ID not found: ";

	@Autowired
	InterviewRepository interviewRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public InterviewDTO addNewInterview(InterviewDTO interviewDTO) {

		Interview newInterview = this.interviewRepository.save(InterviewDTOToEntity(interviewDTO));
		return convertToInterviewDTO(newInterview);
	}

	@Override
	public InterviewDTO deleteInterview(Long interviewId) throws InterviewNotFoundException {

		Interview interviewToDelete = this.interviewRepository.findById(interviewId)
				.orElseThrow(() -> new InterviewNotFoundException(INTERVIEW_NOT_FOUND_STRING + interviewId));
		this.interviewRepository.deleteById(interviewId);

		return convertToInterviewDTO(interviewToDelete);
	}

	@Override
	public InterviewDTO updateInterviewStatus(Long interviewId, String updatedStatus)
			throws InterviewNotFoundException {

		Interview interviewToUpdate = this.interviewRepository.findById(interviewId)
				.orElseThrow(() -> new InterviewNotFoundException(INTERVIEW_NOT_FOUND_STRING + interviewId));

		interviewToUpdate.setInterviewStatus(updatedStatus);
		
		return (convertToInterviewDTO(this.interviewRepository.save(interviewToUpdate)));
	}


	@Override
	public List<InterviewDTO> getInterviewByName(String interviewName) throws InterviewNotFoundException {

		List<Interview> interviewsList = this.interviewRepository.getInterviewByName(interviewName);

		if (interviewsList.size() > 0)
			return (convertToInterviewDTO(interviewsList));
		else
			throw new InterviewNotFoundException(INTERVIEW_NOT_FOUND_STRING + interviewName);

	}

	@Override
	public List<InterviewDTO> getInterviewsByInterviewer(String interviewerName) throws InterviewNotFoundException {

		List<Interview> interviewsList = this.interviewRepository.getInterviewByName(interviewerName);

		if (interviewsList.size() > 0)
			return (convertToInterviewDTO(interviewsList));
		else
			throw new InterviewNotFoundException(INTERVIEW_NOT_FOUND_STRING + interviewerName);
	}

	@Override
	public List<InterviewDTO> getInterviewByInterViewerandInterviewName(String interviewerName, String interviewName)
			throws InterviewNotFoundException {

		List<Interview> interviewsList = this.interviewRepository
				.getInterviewByInterviewerandInterviewName(interviewerName, interviewName);

		if (interviewsList.size() > 0)
			return (convertToInterviewDTO(interviewsList));
		else
			throw new InterviewNotFoundException(
					INTERVIEW_NOT_FOUND_STRING + interviewerName + " for Interview - " + interviewName);
	}

	@Override
	public List<InterviewDTO> getAllInterviews() {

		List<Interview> interviewsList = this.interviewRepository.findAll();

		return (convertToInterviewDTO(interviewsList));
	}

	@Override
	public InterviewDTO getInterviewWithId(long interviewId) {

		Interview interview = this.interviewRepository.findById(interviewId)
				.orElseThrow(() -> new InterviewNotFoundException(INTERVIEW_NOT_FOUND_STRING + interviewId));

		return (convertToInterviewDTO(interview));
	}

	private InterviewDTO convertToInterviewDTO(Interview interview) {
		InterviewDTO InterviewDTO = modelMapper.map(interview, InterviewDTO.class);
		return InterviewDTO;
	}

	private List<InterviewDTO> convertToInterviewDTO(List<Interview> interview) {
		return interview.stream().map(interviewEntity -> convertToInterviewDTO(interviewEntity))
				.collect(Collectors.toList());
	}

	private Interview InterviewDTOToEntity(InterviewDTO interviewDTO) {
		Interview InterviewEntity = modelMapper.map(interviewDTO, Interview.class);
		return InterviewEntity;
	}

}

package com.wf.training.sba3.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.wf.training.sba3.dto.AttendeeDTO;
import com.wf.training.sba3.entity.Attendee;
import com.wf.training.sba3.entity.Interview;
import com.wf.training.sba3.entity.User;
import com.wf.training.sba3.exception.AttendeeNotFoundException;
import com.wf.training.sba3.repository.AttendeeRepository;
import com.wf.training.sba3.repository.InterviewRepository;
import com.wf.training.sba3.repository.UserRepository;
import com.wf.training.sba3.service.AttendeeService;

@Service
public class AttendeeServiceImpl implements AttendeeService {

	@Autowired
	AttendeeRepository attendeeRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	InterviewRepository interviewRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public AttendeeDTO addAttendee(Long interviewId, Long userId) {

		Optional<User> user = userRepository.findById(userId);
		Optional<Interview> interview = interviewRepository.findById(interviewId);

		if (user.isPresent() && interview.isPresent()) {

			Attendee attendee = new Attendee();
			attendee.setInterview(interview.get());
			attendee.setUser(user.get());

			ExampleMatcher modelMatcher = ExampleMatcher.matching().withIgnorePaths("id")
					.withMatcher("user", match -> match.ignoreCase())
					.withMatcher("interview", match -> match.ignoreCase());
			Example<Attendee> attendeeExample = Example.of(attendee, modelMatcher);

			if (!attendeeRepository.exists(attendeeExample)) {
				Attendee attendeeEntity = attendeeRepository.save(attendee);
				return convertToAttendeeDTO(attendeeEntity);
			} else {
				throw new AttendeeNotFoundException(
						"record with given data already exists.\n[Inertview ID: " + interviewId + "User ID: " + userId);
			}

		} else {
			throw new AttendeeNotFoundException("Interview/User details for given InterviewId/UserId not present."
					+ "Inertview ID: " + interviewId + "User ID: " + userId);
		}
	}

	@Override
	public List<AttendeeDTO> getAllAttendees() {
		List<Attendee> attendeesList = attendeeRepository.findAll();
		return convertToAttendeeDTO(attendeesList);
	}

	@Override
	public List<AttendeeDTO> findAttendeeByUserID(Long userId) {

		List<Attendee> attendees = attendeeRepository.findAttendeByUserID(userId);
		if (attendees.size() > 0)
			return convertToAttendeeDTO(attendees);

		else
			throw new AttendeeNotFoundException("Attendee with the following user ID not found: " + userId);
	}

	@Override
	public List<AttendeeDTO> findAttendeeByInterviewID(Long interviewId) {

		List<Attendee> attendees = attendeeRepository.findAttendeByInterviewID(interviewId);
		if (attendees.size() > 0)
			return convertToAttendeeDTO(attendees);

		else
			throw new AttendeeNotFoundException("No attendee record present for given Interview id: " + interviewId);

	}

	private AttendeeDTO convertToAttendeeDTO(Attendee attendee) {
		AttendeeDTO attendeeDTO = modelMapper.map(attendee, AttendeeDTO.class);
		return attendeeDTO;
	}

	private List<AttendeeDTO> convertToAttendeeDTO(List<Attendee> attendee) {

		return attendee.stream().map(attendeeEnity -> convertToAttendeeDTO(attendeeEnity)).collect(Collectors.toList());
	}
}

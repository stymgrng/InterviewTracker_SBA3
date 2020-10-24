package com.wf.training.sba3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wf.training.sba3.dto.AttendeeDTO;
import com.wf.training.sba3.service.AttendeeService;

@RestController
@RequestMapping("/attendees")
public class AttendeeController {

	@Autowired
	AttendeeService attendeeService;

	@GetMapping("")
	public ResponseEntity<List<AttendeeDTO>> getAllAttendees() {

		List<AttendeeDTO> list = attendeeService.getAllAttendees();

		return new ResponseEntity<List<AttendeeDTO>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/interviewscount")
	public ResponseEntity<String> getTotalInterviews() {

		List<AttendeeDTO> list = attendeeService.getAllAttendees();

		return new ResponseEntity<String>("Total no of interview conducted :- " + list.size(), new HttpHeaders(),
				HttpStatus.OK);
	}

	@PostMapping("/add/{interviewId}/{userID}")
	public ResponseEntity<AttendeeDTO> addInterview(@PathVariable("interviewId") Long interviewId,
			@PathVariable("userID") Long userId) {

		AttendeeDTO attendeeDTO = attendeeService.addAttendee(interviewId, userId);

		return new ResponseEntity<AttendeeDTO>(attendeeDTO, new HttpHeaders(), HttpStatus.OK);

	}

	@GetMapping("/Interview/{id}")
	public ResponseEntity<List<AttendeeDTO>> getAttendeeByInterviewId(@PathVariable("id") Long id) {

		List<AttendeeDTO> attendeeDTO = attendeeService.findAttendeeByInterviewID(id);

		return new ResponseEntity<List<AttendeeDTO>>(attendeeDTO, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/User/{id}")
	public ResponseEntity<List<AttendeeDTO>> getAttendeeByUserId(@PathVariable("id") Long id) {

		List<AttendeeDTO> attendeeDTO = attendeeService.findAttendeeByUserID(id);

		return new ResponseEntity<List<AttendeeDTO>>(attendeeDTO, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/UserAttendCount/{id}")
	public ResponseEntity<String> getAttendeeCountByUserId(@PathVariable("userId") Long userId) {

		List<AttendeeDTO> attendeeDTO = attendeeService.findAttendeeByUserID(userId);

		return new ResponseEntity<String>(
				"Count of interviews attended/scheduled for user ID: " + userId + " : " + attendeeDTO.size(),
				new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/InterviewConductedCount/{id}")
	public ResponseEntity<String> getAttendeeCountByInterviewId(@PathVariable("userId") Long userId) {
		List<AttendeeDTO> attendeeDTO = attendeeService.findAttendeeByInterviewID(userId);

		return new ResponseEntity<String>(
				"Count of interviews schedulled for the interview id-" + userId + " : " + attendeeDTO.size(),
				new HttpHeaders(), HttpStatus.OK);
	}

}

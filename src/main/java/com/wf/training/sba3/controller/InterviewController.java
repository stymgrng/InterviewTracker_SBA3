package com.wf.training.sba3.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wf.training.sba3.dto.InterviewDTO;
import com.wf.training.sba3.exception.InterviewNotFoundException;
import com.wf.training.sba3.service.InterviewService;

@RestController
@RequestMapping("/interviews")
public class InterviewController {

	@Autowired
	InterviewService interviewService;

	@PostMapping("")
	public ResponseEntity<InterviewDTO> addNewInterview(@Valid @RequestBody InterviewDTO interviewDTO) {

		InterviewDTO addedinterviewDTO = this.interviewService.addNewInterview(interviewDTO);

		return new ResponseEntity<InterviewDTO>(addedinterviewDTO, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/{interviewId}")
	public ResponseEntity<InterviewDTO> deleteInterview(@PathVariable("interviewId") Long interviewId)
			throws InterviewNotFoundException {

		InterviewDTO interviewToDeleteDTO = this.interviewService.deleteInterview(interviewId);

		return new ResponseEntity<InterviewDTO>(interviewToDeleteDTO, new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping("/{interviewId}/{status}")
	public ResponseEntity<InterviewDTO> updateInterview(@PathVariable("interviewId") Long interviewId,
			@PathVariable("status") String status) {

		System.out.println("Inside - updateInterview - Controller");
		
		InterviewDTO addedInterviewDTO = this.interviewService.updateInterviewStatus(interviewId, status);

		return new ResponseEntity<InterviewDTO>(addedInterviewDTO, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{interviewId}")
	public ResponseEntity<InterviewDTO> getInterviewWithId(@PathVariable("interviewId") Long interviewId) {

		InterviewDTO interviews = this.interviewService.getInterviewWithId(interviewId);

		return new ResponseEntity<InterviewDTO>(interviews, HttpStatus.OK);
	}

	@GetMapping("/interviewName/{interviewName}")
	public ResponseEntity<List<InterviewDTO>> getInterviewByName(@PathVariable("interviewName") String interviewName) {

		List<InterviewDTO> interviewList = this.interviewService.getInterviewByName(interviewName);

		return new ResponseEntity<List<InterviewDTO>>(interviewList, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{interviewerName}/{interviewName}")
	public ResponseEntity<List<InterviewDTO>> getInterviewByInterViewerandInterviewName(
			@PathVariable("interviewerName") String interviewerName,
			@PathVariable("interviewName") String interviewName) {

		List<InterviewDTO> interviewList = this.interviewService
				.getInterviewByInterViewerandInterviewName(interviewerName, interviewName);

		return new ResponseEntity<List<InterviewDTO>>(interviewList, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<List<InterviewDTO>> getAllInterviews() {

		List<InterviewDTO> interviews = this.interviewService.getAllInterviews();

		return new ResponseEntity<List<InterviewDTO>>(interviews, HttpStatus.OK);
	}

	@GetMapping("/totalCount")
	public ResponseEntity<String> getTotalInterviewCount() {

		List<InterviewDTO> interviewList = this.interviewService.getAllInterviews();

		return new ResponseEntity<String>("Total Count of Interviews: " + interviewList.size(), new HttpHeaders(),
				HttpStatus.OK);
	}

}

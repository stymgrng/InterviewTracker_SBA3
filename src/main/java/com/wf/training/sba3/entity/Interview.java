package com.wf.training.sba3.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Interview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment
	private Long interviewId;

	private String interviewerName;
	private String interviewName;
	private String userSkills;
	private LocalTime time;
	private LocalDate date;
	private String interviewStatus;
	private String remarks;

	@OneToMany(mappedBy = "interview", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Attendee> attendee;

	@PrePersist
	public void prePersist() {
		time = LocalTime.now();
		date = LocalDate.now();
	}
}

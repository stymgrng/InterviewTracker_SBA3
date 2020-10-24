package com.wf.training.sba3.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data // auto-maps this class with table User in db
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment
	// @Column(name="USERID")
	private Long userId;

	// @Column(name="FIRSTNAME")
	private String firstName;
	// @Column(name="LASTNAME")
	private String lastName;
	// @Column(name = "EMAIL")
	private String email;
	// @Column(name = "MOBILE")
	private String mobile;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Attendee> attendee;

}

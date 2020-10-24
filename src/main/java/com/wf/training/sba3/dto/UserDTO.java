package com.wf.training.sba3.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

	private Long userId;

	@NotBlank(message = "First Name cannot be Blank.")
	@Size(min = 5, max = 30, message = "First Name should have a minimum of 5 and a maximum of 30 characters.")
	private String firstName;

	@NotBlank(message = "Last Name cannot be Blank.")
	@Size(min = 5, max = 25, message = "First Name should have a minimum of 5 and a maximum of 25 characters.")
	private String lastName;
	
	@NotBlank(message = "Email Address cannot be Blank.")
	@Email(message = "Email should be a valid Email Address.")
	private String email;
	
	@NotBlank(message = "Mobile Number cannot be Blank.")
	@Length(min = 10,max = 10,message = "Mobile number should be a valid number with 10 digits.")
	private String mobile;

}

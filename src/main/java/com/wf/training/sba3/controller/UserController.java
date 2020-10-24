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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wf.training.sba3.dto.UserDTO;
import com.wf.training.sba3.exception.UserNotFoundException;
import com.wf.training.sba3.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("")
	public ResponseEntity<List<UserDTO>> getAllUsers() {

		List<UserDTO> userDTOList = this.userService.getAllUsers();

		return new ResponseEntity<List<UserDTO>>(userDTOList, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUserWithId(@PathVariable("userId") Long userId) throws UserNotFoundException {

		UserDTO userDTO = this.userService.getUserById(userId);

		return new ResponseEntity<UserDTO>(userDTO, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<UserDTO> addNewUser(@Valid @RequestBody UserDTO userDTO) {

		UserDTO addedUserDTO = this.userService.addNewUser(userDTO);

		return new ResponseEntity<UserDTO>(addedUserDTO, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<UserDTO> deleteExistingUser(@PathVariable("userId") Long userId)
			throws UserNotFoundException {

		UserDTO userToDeleteDTO = this.userService.deleteUser(userId);

		return new ResponseEntity<UserDTO>(userToDeleteDTO, new HttpHeaders(), HttpStatus.OK);
	}

}

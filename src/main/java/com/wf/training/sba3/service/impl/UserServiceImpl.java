package com.wf.training.sba3.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wf.training.sba3.dto.UserDTO;
import com.wf.training.sba3.entity.User;
import com.wf.training.sba3.exception.UserNotFoundException;
import com.wf.training.sba3.repository.UserRepository;
import com.wf.training.sba3.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private static final String USER_NOT_FOUND_STRING = "User not found for this id: ";

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<UserDTO> getAllUsers() {

		List<UserDTO> usersDTO;
		List<User> users = this.userRepository.findAll();

		usersDTO = users.stream().
				map(user -> new UserDTO(user.getUserId(), 
						user.getFirstName(), 
						user.getLastName(), 
						user.getEmail(), 
						user.getMobile())).collect(Collectors.toList());

		return usersDTO;
	}

	@Override
	public UserDTO getUserById(Long userId) throws UserNotFoundException {

		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_STRING + userId));

		UserDTO userDTO = new UserDTO();

		userDTO.setUserId(user.getUserId());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setEmail(user.getEmail());
		userDTO.setMobile(user.getMobile());

		return userDTO;
	}

	@Override
	public UserDTO addNewUser(UserDTO newUserDTO) {

		User newUser = new User();
		newUser.setFirstName(newUserDTO.getFirstName());	
		newUser.setLastName(newUserDTO.getLastName());
		newUser.setEmail(newUserDTO.getEmail());
		newUser.setMobile(newUserDTO.getMobile());

		User addedUser = this.userRepository.save(newUser);
		UserDTO addedUserDTO = new UserDTO(addedUser.getUserId(), 
				addedUser.getFirstName(), 
				addedUser.getLastName(), 
				addedUser.getEmail(), 
				addedUser.getMobile());

		return addedUserDTO;
	}

	@Override
	public UserDTO deleteUser(Long userId) throws UserNotFoundException {

		User userToDelete = this.userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_STRING + userId));

		this.userRepository.deleteById(userId);
		
		UserDTO deletedUserDTO = new UserDTO(userToDelete.getUserId(), 
				userToDelete.getFirstName(), 
				userToDelete.getLastName(), 
				userToDelete.getEmail(), 
				userToDelete.getMobile());
		
		
		return deletedUserDTO;
	}

}

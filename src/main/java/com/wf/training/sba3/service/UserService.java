package com.wf.training.sba3.service;

import java.util.List;

import com.wf.training.sba3.dto.UserDTO;

public interface UserService {

	public List<UserDTO> getAllUsers();

	public UserDTO getUserById(Long userId);

	public UserDTO addNewUser(UserDTO userDTO);

	public UserDTO deleteUser(Long userId);

}

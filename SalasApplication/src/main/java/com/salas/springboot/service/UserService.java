package com.salas.springboot.service;

import java.util.List;

import com.salas.springboot.dto.UserDTO;

public interface UserService {

	UserDTO findById(Long id);

	UserDTO findByName(String name);

	void saveUser(UserDTO user);

	boolean isUserExist(UserDTO user);

	List<UserDTO> findAllUsers();

}

package com.salas.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salas.springboot.dto.UserDTO;
import com.salas.springboot.model.User;
import com.salas.springboot.repositories.UserRepository;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public UserDTO findById(Long id) {
		return convertToDTO(userRepository.findById(id));
	}

	@Override
	public void saveUser(UserDTO usuario) {
		userRepository.save(convertToEntity(usuario));
	}

	@Override
	public UserDTO findByName(String name) {
		return convertToDTO(userRepository.findByName(name));
	}

	@Override
	public boolean isUserExist(UserDTO user) {
		return findByName(user.getName()) != null;
	}

	@Override
	public List<UserDTO> findAllUsers() {
		List<UserDTO> users = null;
		List<User> entitys = userRepository.findAll();

		if (Objects.nonNull(entitys) && !entitys.isEmpty()) {
			users = new ArrayList<>();
			for (User user : entitys) {
				users.add(convertToDTO(user));
			}
		}

		return users;
	}

	public static UserDTO convertToDTO(User user) {
		UserDTO dto = null;
		if (Objects.nonNull(user)) {
			dto = new UserDTO();
			dto.setId(user.getId());
			dto.setName(user.getName());
			dto.setRol(user.getRol());
		}
		return dto;
	}

	public static User convertToEntity(UserDTO user) {
		User entity = null;
		if (Objects.nonNull(user)) {
			entity = new User();
			entity.setId(user.getId());
			entity.setName(user.getName());
			entity.setRol(user.getRol());
		}
		return entity;
	}

}

package com.salas.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salas.springboot.model.User;
import com.salas.springboot.repositories.UserRepository;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public User findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public void saveUser(User usuario) {
		userRepository.save(usuario);
	}

	@Override
	public User findByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public boolean isUserExist(User user) {
		return findByName(user.getName()) != null;
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

}

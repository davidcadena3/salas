package com.salas.springboot.service;

import java.util.List;

import com.salas.springboot.model.User;

public interface UserService {

	User findById(Long id);

	User findByName(String name);

	void saveUser(User user);

	boolean isUserExist(User user);

	List<User> findAllUsers();

}

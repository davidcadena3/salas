package com.salas.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salas.springboot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findById(Long id);

	User findByName(String name);

}
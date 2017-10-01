package com.salas.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salas.springboot.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

	Room findById(Long id);

	Room findByName(String name);

}
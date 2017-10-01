package com.salas.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salas.springboot.model.Room;
import com.salas.springboot.repositories.RoomRepository;

@Service("roomService")
@Transactional
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomRepository;

	public Room findById(Long id) {
		return roomRepository.findById(id);
	}

	@Override
	public void saveRoom(Room room) {
		roomRepository.save(room);
	}

	@Override
	public Room findByName(String name) {
		return roomRepository.findByName(name);
	}

	@Override
	public boolean isRoomExist(Room room) {
		return findByName(room.getName()) != null;
	}

	@Override
	public List<Room> findAllRooms() {
		return roomRepository.findAll();
	}

}

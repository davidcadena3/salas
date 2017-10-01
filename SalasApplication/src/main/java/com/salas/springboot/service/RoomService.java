package com.salas.springboot.service;

import java.util.List;

import com.salas.springboot.model.Room;

public interface RoomService {

	Room findById(Long id);

	Room findByName(String name);

	void saveRoom(Room room);

	boolean isRoomExist(Room room);

	List<Room> findAllRooms();

}

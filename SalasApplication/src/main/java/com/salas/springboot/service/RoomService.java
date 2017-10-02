package com.salas.springboot.service;

import java.util.List;

import com.salas.springboot.dto.RoomDTO;

public interface RoomService {

	RoomDTO findById(Long id);

	RoomDTO findByName(String name);

	void saveRoom(RoomDTO room);

	boolean isRoomExist(RoomDTO room);

	List<RoomDTO> findAllRooms();

}

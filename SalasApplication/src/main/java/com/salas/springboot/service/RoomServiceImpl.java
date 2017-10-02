package com.salas.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salas.springboot.dto.RoomDTO;
import com.salas.springboot.model.Room;
import com.salas.springboot.repositories.RoomRepository;

@Service("roomService")
@Transactional
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomRepository;

	public RoomDTO findById(Long id) {
		return convertToDTO(roomRepository.findById(id));
	}

	@Override
	public void saveRoom(RoomDTO room) {
		roomRepository.save(convertToEntity(room));
	}

	@Override
	public RoomDTO findByName(String name) {
		return convertToDTO(roomRepository.findByName(name));
	}

	@Override
	public boolean isRoomExist(RoomDTO room) {
		return findByName(room.getName()) != null;
	}

	@Override
	public List<RoomDTO> findAllRooms() {
		List<RoomDTO> rooms = null;
		List<Room> entitys = roomRepository.findAll();

		if (Objects.nonNull(entitys) && !entitys.isEmpty()) {
			rooms = new ArrayList<>();
			for (Room room : entitys) {
				rooms.add(convertToDTO(room));
			}
		}

		return rooms;
	}

	public static RoomDTO convertToDTO(Room room) {
		RoomDTO dto = null;
		if (Objects.nonNull(room)) {
			dto = new RoomDTO();
			dto.setCapacity(room.getCapacity());
			dto.setId(room.getId());
			dto.setName(room.getName());
		}
		return dto;
	}

	public static Room convertToEntity(RoomDTO room) {
		Room entity = null;
		if (Objects.nonNull(room)) {
			entity = new Room();
			entity.setCapacity(room.getCapacity());
			entity.setId(room.getId());
			entity.setName(room.getName());
		}
		return entity;
	}

}

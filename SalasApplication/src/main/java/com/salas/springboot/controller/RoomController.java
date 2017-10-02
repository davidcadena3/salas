package com.salas.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.salas.springboot.dto.RoomDTO;
import com.salas.springboot.service.RoomService;
import com.salas.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/room")
public class RoomController {

    public static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    RoomService roomService;

    /**
     * Retornar todas las salas
     * 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<RoomDTO>> listAllRooms() {
	List<RoomDTO> rooms = roomService.findAllRooms();
	if (rooms.isEmpty()) {
	    return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	return new ResponseEntity<List<RoomDTO>>(rooms, HttpStatus.OK);
    }

    /**
     * Retornar una sala por su id
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getRoom(@PathVariable("id") long id) {
	logger.info("get room con id {}", id);
	RoomDTO room = roomService.findById(id);
	if (room == null) {
	    logger.error("sala con id {} no encontrada.", id);
	    return new ResponseEntity(new CustomErrorType("sala con id " + id + " no encontrado"),
		    HttpStatus.NOT_FOUND);
	}
	return new ResponseEntity<RoomDTO>(room, HttpStatus.OK);
    }

    /**
     * Crear una sala
     * 
     * @param room
     * @param ucBuilder
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createRoom(@RequestBody RoomDTO room) {
	logger.info("creando sala: {}", room);
	if (roomService.isRoomExist(room)) {
	    logger.error("La sala " + room.getName() + " ya existe ");
	    return new ResponseEntity(new CustomErrorType("La sala " + room.getName() + " ya existe "),
		    HttpStatus.CONFLICT);
	}
	roomService.saveRoom(room);

	return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }

}

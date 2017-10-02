package com.salas.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.salas.springboot.dto.UserDTO;
import com.salas.springboot.service.UserService;
import com.salas.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/user")
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    /**
     * Retornar todos los usuarios
     * 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> listAllUsers() {
	List<UserDTO> users = userService.findAllUsers();
	if (users.isEmpty()) {
	    return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
    }

    /**
     * Retornar un usuario por su id
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
	logger.info("get usuario con id {}", id);
	UserDTO user = userService.findById(id);
	if (user == null) {
	    logger.error("usuario con id {} no encontrado.", id);
	    return new ResponseEntity(new CustomErrorType("usuario con id " + id + " no encontrado"),
		    HttpStatus.NOT_FOUND);
	}
	return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
    }

    /**
     * Crear un usuario
     * 
     * @param reserva
     * @param ucBuilder
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody UserDTO user) {
	logger.info("creando usuario: {}", user);
	if (userService.isUserExist(user)) {
	    logger.error("El usuario " + user.getName() + " ya existe ");
	    return new ResponseEntity(new CustomErrorType("El usuario " + user.getName() + " ya existe "),
		    HttpStatus.CONFLICT);
	}
	userService.saveUser(user);

	return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }
}

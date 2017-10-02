package com.salas.springboot.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import com.salas.springboot.controller.UserController;
import com.salas.springboot.dto.UserDTO;
import com.salas.springboot.model.User;
import com.salas.springboot.model.enums.ErolUsuario;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
@ActiveProfiles("local")
public class UserTests {

	public static final Logger logger = LoggerFactory.getLogger(UserTests.class);

	@Autowired
	private MockMvc mvc;

	@MockBean
	UserController userController;

	@Test
	public void createUser() {
		logger.info("start create user test");

		UserDTO user = new UserDTO();
		user.setId(200L);
		user.setName("Ricardo");
		user.setRol(ErolUsuario.USUARIO);

		userController.createUser(user, UriComponentsBuilder.newInstance());

		Mockito.when(userController.getUser(200L));

		ResponseEntity<User> response = (ResponseEntity<User>) userController.getUser(200L);
		User userCreated = response.getBody();
		Assert.assertNotNull(userCreated);
		logger.info("end create user test");
	}

}

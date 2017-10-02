package com.salas.springboot.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.salas.springboot.controller.UserController;
import com.salas.springboot.dto.UserDTO;
import com.salas.springboot.model.enums.ErolUsuario;
import com.salas.springboot.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    UserDTO user;
    UserDTO newUser;

    @Before
    public void setUp() {
	user = new UserDTO();
	user.setId(1L);
	user.setName("David");
	user.setRol(ErolUsuario.USUARIO);
	newUser = new UserDTO();
	newUser.setName("Camilo");
	newUser.setRol(ErolUsuario.USUARIO);
    }

    @Test
    public void userNotFound() throws Exception {

	Mockito.when(userService.findByName(Mockito.anyString())).thenReturn(user);

	RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/" + user.getId())
		.accept(MediaType.APPLICATION_JSON);

	MvcResult result = mockMvc.perform(requestBuilder).andReturn();

	System.out.println(result.getResponse().getContentAsString());
	String expected = "{\"errorMessage\":\"usuario con id 1 no encontrado\"}";

	JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void userFound() throws Exception {

	Mockito.when(userService.findById(Mockito.anyLong())).thenReturn(user);

	RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/" + user.getId())
		.accept(MediaType.APPLICATION_JSON);

	MvcResult result = mockMvc.perform(requestBuilder).andReturn();

	System.out.println(result.getResponse().getContentAsString());
	String expected = "{\"id\":1,\"name\":\"David\",\"rol\":\"USUARIO\"}";

	JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void createUser() throws Exception {

	Mockito.when(userService.isUserExist(Mockito.anyObject())).thenReturn(false);

	RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON)
		.content("{\"id\":10,\"name\":\"Camilo\",\"rol\":\"USUARIO\"}");

	MvcResult result = mockMvc.perform(requestBuilder).andReturn();

	System.out.println(result.getResponse().getContentAsString());

	Assert.assertTrue(result.getResponse().getContentAsString().equals("true"));
    }

}

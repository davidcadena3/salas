package com.salas.springboot.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.salas.springboot.controller.RoomController;
import com.salas.springboot.dto.RoomDTO;
import com.salas.springboot.service.RoomService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RoomController.class, secure = false)
public class RoomControllerTest {

    public static final Logger logger = LoggerFactory.getLogger(RoomControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    RoomDTO room;
    RoomDTO newRoom;

    @Before
    public void setUp() {
	room = new RoomDTO();
	room.setId(1L);
	room.setName("Room 1");
	room.setCapacity(100);
	newRoom = new RoomDTO();
	newRoom.setName("Room 2");
	newRoom.setCapacity(1000);
    }

    @Test
    public void roomNotFound() throws Exception {

	Mockito.when(roomService.findById(Mockito.anyLong())).thenReturn(null);

	RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/room/" + room.getId())
		.accept(MediaType.APPLICATION_JSON);

	MvcResult result = mockMvc.perform(requestBuilder).andReturn();

	logger.info(result.getResponse().getContentAsString());
	String expected = "{\"errorMessage\":\"sala con id 1 no encontrado\"}";

	JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void roomFound() throws Exception {

	Mockito.when(roomService.findById(Mockito.anyLong())).thenReturn(room);

	RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/room/" + room.getId())
		.accept(MediaType.APPLICATION_JSON);

	MvcResult result = mockMvc.perform(requestBuilder).andReturn();

	logger.info(result.getResponse().getContentAsString());
	String expected = "{\"id\":1,\"capacity\":100,\"name\":\"Room 1\"}";

	JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void createRoom() throws Exception {

	Mockito.when(roomService.isRoomExist(Mockito.anyObject())).thenReturn(false);

	RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/room").contentType(MediaType.APPLICATION_JSON)
		.content("{\"id\":10,\"name\":\"Room 2\",\"capacity\":1000}");

	MvcResult result = mockMvc.perform(requestBuilder).andReturn();

	logger.info(result.getResponse().getContentAsString());

	Assert.assertTrue(result.getResponse().getContentAsString().equals("true"));
    }

}

package com.salas.springboot.test;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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

import com.salas.springboot.controller.ReservationController;
import com.salas.springboot.dto.ReservationDTO;
import com.salas.springboot.model.enums.EreservationStatus;
import com.salas.springboot.service.ReservationService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ReservationController.class, secure = false)
public class ReservationControllerTest {

    public static final Logger logger = LoggerFactory.getLogger(ReservationControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    ReservationDTO reservation;

    @Before
    public void setUp() {
	reservation = new ReservationDTO();
	reservation.setId(1L);
	reservation.setDate(new Date());
	reservation.setReservedCapacity(100);
	reservation.setStatus(EreservationStatus.SOLICITADA);
    }

    @Test
    public void updateReservation() throws Exception {
    }

    @Test
    public void deleteReservation() throws Exception {
    }

    @Test
    public void createReservation() throws Exception {

	Mockito.when(reservationService.isReservaExist(Mockito.anyObject())).thenReturn(false);

	RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/room").contentType(MediaType.APPLICATION_JSON)
		.content("{\"id\":1,\"date\":\"2018-10-10 11:00:00\",\"reservedCapacity\":30}");

	MvcResult result = mockMvc.perform(requestBuilder).andReturn();

	logger.info(result.getResponse().getContentAsString());

	Assert.assertTrue(result.getResponse().getContentAsString().equals("true"));
    }

}

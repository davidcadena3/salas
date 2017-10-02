package com.salas.springboot.test;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.salas.springboot.model.Reservation;
import com.salas.springboot.model.Room;
import com.salas.springboot.model.User;
import com.salas.springboot.model.enums.EreservationStatus;
import com.salas.springboot.model.enums.ErolUsuario;
import com.salas.springboot.repositories.ReservationRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReservationRepositoryTests {

    public static final Logger logger = LoggerFactory.getLogger(ReservationRepositoryTests.class);

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ReservationRepository reservationRepository;

    private Reservation reservation;
    private Room room;
    private User user;

    @Before
    public void setUp() {
	logger.info("setUp start");
	reservation = new Reservation();
	reservation.setId(10L);
	reservation.setDate(new Date());
	reservation.setReservedCapacity(50);
	reservation.setStatus(EreservationStatus.SOLICITADA);

	room = new Room();
	room.setName("Room 1");
	room.setCapacity(100);

	user = new User();
	user.setName("User 1");
	user.setRol(ErolUsuario.USUARIO);
	logger.info("setUp end");
    }

    @Test
    public void findByRoomAndUserAndStatusAndDate() {
	logger.info("findByRoomAndUserAndStatusAndDate start");
	room = entityManager.persist(room);
	user = entityManager.persist(user);

	reservation.setRoom(room);
	reservation.setUser(user);

	entityManager.persist(reservation);
	Reservation response = reservationRepository.findByRoomAndUserAndStatusAndDate(room, user,
		EreservationStatus.SOLICITADA, reservation.getDate());
	Assert.assertEquals(reservation, response);

	logger.info("findByRoomAndUserAndStatusAndDate end");
    }

}

package com.salas.springboot.test;

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

import com.salas.springboot.model.Room;
import com.salas.springboot.repositories.RoomRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoomRepositoryTests {

    public static final Logger logger = LoggerFactory.getLogger(RoomRepositoryTests.class);

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private RoomRepository roomRepository;

    private Room room;

    @Before
    public void setUp() {
	logger.info("setUp start");
	room = new Room();
	room.setName("Ricardo");
	room.setCapacity(100);
	logger.info("setUp end");
    }

    @Test
    public void createRoomSucessfuly() {
	logger.info("findByName start");

	entityManager.persist(room);
	Room response = roomRepository.findByName(room.getName());
	Assert.assertEquals(room.getName(), response.getName());

	logger.info("findByName end");
    }

}

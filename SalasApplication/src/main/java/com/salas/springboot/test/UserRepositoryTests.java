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

import com.salas.springboot.model.User;
import com.salas.springboot.model.enums.ErolUsuario;
import com.salas.springboot.repositories.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTests {

    public static final Logger logger = LoggerFactory.getLogger(UserRepositoryTests.class);

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    private User user;

    @Before
    public void setUp() {
	logger.info("setUp start");
	user = new User();
	user.setName("Ricardo");
	user.setRol(ErolUsuario.USUARIO);
	logger.info("setUp end");
    }

    @Test
    public void findByName() {
	logger.info("findByName start");

	entityManager.persist(user);
	User response = userRepository.findByName(user.getName());
	Assert.assertEquals(user.getName(), response.getName());

	logger.info("findByName end");
    }

}

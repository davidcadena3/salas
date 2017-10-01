package com.salas.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.salas.springboot.configuration.JpaConfiguration;


@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.salas.springboot"})
public class SalasApp {

	public static void main(String[] args) {
		SpringApplication.run(SalasApp.class, args);
	}
}

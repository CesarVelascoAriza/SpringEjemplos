package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.service.IPersonaService;

@SpringBootApplication
public class DemoPruebaApplication implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(DemoPruebaApplication.class);
	@SuppressWarnings("unused")
	@Autowired
	private IPersonaService servicePersona;

	
	public static void main(String[] args) {
		SpringApplication.run(DemoPruebaApplication.class, args);
	}
	

	public void run(String... args) throws Exception {
		logger.info("Hola mundo");
		servicePersona.registrar("Cesar");
	}

}

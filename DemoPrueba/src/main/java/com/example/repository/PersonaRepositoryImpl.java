package com.example.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.DemoPruebaApplication;

@Repository
public class PersonaRepositoryImpl implements IPersonaRepo{

	private static Logger logger= LoggerFactory.getLogger(DemoPruebaApplication.class);
	@Override
	public void registrar(String nombre) {
		// TODO Auto-generated method stub
		logger.info("Hola" + nombre);
	}

	
}

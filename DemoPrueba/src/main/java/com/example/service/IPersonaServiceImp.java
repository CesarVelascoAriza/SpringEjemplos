package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.IPersonaRepo;

@Service
public class IPersonaServiceImp implements IPersonaService {

	@Autowired
	private IPersonaRepo persona;
	
	
	public void registrar(String nombre) {
		// TODO Auto-generated method stub
		persona.registrar(nombre);
	}

}

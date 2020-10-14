package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Persona;
import com.example.demo.repo.IPersonaRepo;

@RestController
@RequestMapping("/personas")
public class RestControler {

	@Autowired
	private IPersonaRepo repo;
	
	@GetMapping()
	public List<Persona> getpersona(){
		return repo.findAll();
	}
	
	@PostMapping()
	public void insertarPersona(Persona per) {
		repo.save(per);
	}
	
}

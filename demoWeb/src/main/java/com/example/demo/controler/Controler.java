package com.example.demo.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Persona;
import com.example.demo.repo.IPersonaRepo;

@Controller
public class Controler {

	@Autowired
	private IPersonaRepo repo;
	
	
	@GetMapping("/")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		
		Persona p= new Persona();
		p.setIdPersona(1);
		p.setNombre("Mito");
		repo.save(p);
		
		
		
		model.addAttribute("name", name);
		return "greeting";
	}
	
	@GetMapping("/listar")
	public String getlistar(Model model) {
		model.addAttribute("personas", repo.findAll());
		return "greeting";
	}
}

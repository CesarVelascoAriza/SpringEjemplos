package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.model.Usuario;
import com.example.demo.repo.IUsuarioRepo;

@SpringBootTest
class DemoWebApplicationTests {

	@Autowired
	private IUsuarioRepo userRepo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Test
	void crearUsuarioTest() {
		Usuario u= new Usuario();
		u.setIdUser(2);
		u.setNombre("prueba");
		u.setClave(encoder.encode("1234"));
		Usuario retorno = userRepo.save(u);
		
		assertTrue(retorno.getClave().equalsIgnoreCase(u.getClave()));
	}

}

package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Persona;

public interface IPersonaRepo extends JpaRepository<Persona, Integer>{


}

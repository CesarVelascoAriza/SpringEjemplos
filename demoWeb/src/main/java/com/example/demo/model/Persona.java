package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Personas", schema = "pruebaspring")
public class Persona {

	@Id
	private int idPersona;
	@Column(name = "nombre", length = 50)
	private String nombre;
	
	public Persona() {
		// TODO Auto-generated constructor stub
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}

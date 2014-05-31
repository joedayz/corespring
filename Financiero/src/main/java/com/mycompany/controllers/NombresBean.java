package com.mycompany.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class NombresBean {

	private String nombre;
	private List<String> nombres = new ArrayList<>();
	
	public void agregar(){
		this.nombres.add(nombre);
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<String> getNombres() {
		return nombres;
	}
	public void setNombres(List<String> nombres) {
		this.nombres = nombres;
	}
	
	
}

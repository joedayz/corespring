package com.mycompany.controllers;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class HolaBean {

	private String nombre;
	private String apellido;
	private String nombreCompleto;
	
	public void decirHola(){
		this.nombreCompleto = 
				this.nombre.toUpperCase() + 
				" " + this.apellido;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	
}

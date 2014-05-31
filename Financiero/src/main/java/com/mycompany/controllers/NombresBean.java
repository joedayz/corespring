package com.mycompany.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputText;

@ManagedBean
@ApplicationScoped
public class NombresBean {

	private String nombre;
	private List<String> nombres = new ArrayList<>();
	
	private HtmlInputText inputNombre;
	private HtmlCommandButton botonAgregar;
	
	
	public void agregar(){
		
		this.nombres.add(nombre);
		
		if(this.nombres.size()>4){
			this.inputNombre.setDisabled(true);
			this.botonAgregar.setDisabled(true);
			this.botonAgregar.setValue("Muchos nombres asignados");
		}
		
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

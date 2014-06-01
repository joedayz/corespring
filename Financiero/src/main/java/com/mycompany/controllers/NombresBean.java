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

	private String nombre ="jose";
	private List<String> nombres = new ArrayList<>();
	
	private HtmlInputText inputNombre;
	private HtmlCommandButton botonAgregar;
	
	private String recumenCurriculum;
	private String mensaje;
	
	private boolean acepto;
	
	private Integer[] asuntosInteres;
	
	
	
	
	public Integer[] getAsuntosInteres() {
		return asuntosInteres;
	}


	public void setAsuntosInteres(Integer[] asuntosInteres) {
		this.asuntosInteres = asuntosInteres;
	}


	public boolean isAcepto() {
		return acepto;
	}


	public void setAcepto(boolean acepto) {
		this.acepto = acepto;
	}


	public String[] getEquiposFavoritos() {
		return equiposFavoritos;
	}


	public void setEquiposFavoritos(String[] equiposFavoritos) {
		this.equiposFavoritos = equiposFavoritos;
	}


	private String equipoFavorito;
	private String[] equiposFavoritos;
	
	
	
	
	
	
	public String getEquipoFavorito() {
		return equipoFavorito;
	}


	public void setEquipoFavorito(String equipoFavorito) {
		this.equipoFavorito = equipoFavorito;
	}


	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	public String getRecumenCurriculum() {
		return recumenCurriculum;
	}


	public void setRecumenCurriculum(String recumenCurriculum) {
		this.recumenCurriculum = recumenCurriculum;
	}


	public String agregar(){
		
		this.nombres.add(nombre);
		
		if(this.nombres.size()>4){
			this.inputNombre.setDisabled(true);
			this.botonAgregar.setDisabled(true);
			this.botonAgregar.setValue("Muchos nombres asignados");
			//return "hola";  //busque la vista
			return "hola?faces-redirect=true";
		}
		
		return null; //se quede en la misma pagina
		
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


	public HtmlInputText getInputNombre() {
		return inputNombre;
	}


	public void setInputNombre(HtmlInputText inputNombre) {
		this.inputNombre = inputNombre;
	}


	public HtmlCommandButton getBotonAgregar() {
		return botonAgregar;
	}


	public void setBotonAgregar(HtmlCommandButton botonAgregar) {
		this.botonAgregar = botonAgregar;
	}
	
	
	
}

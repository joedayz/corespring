package com.mycompany.model;

public enum TipoMovimiento {

	INGRESO("Ingreso"), SALIDA("Salida");
	
	private String descripcion;
	
	TipoMovimiento(String descripcion){
		this.descripcion = descripcion;
	}
	
	public String getDescripcion(){
		return descripcion;
	}
}

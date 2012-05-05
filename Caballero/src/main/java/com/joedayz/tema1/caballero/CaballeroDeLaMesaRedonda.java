package com.joedayz.tema1.caballero;

public class CaballeroDeLaMesaRedonda implements Caballero{

	private String nombre;
	private Aventura aventura;
	
	public CaballeroDeLaMesaRedonda(String nombre) {
		this.nombre = nombre;
	}

	public Object embarcarEnAventura() throws AventuraFailedException {
		
		return aventura.embarcar();
	}

	public String getNombre() {
		return nombre;
	}

	public void setAventura(Aventura aventura) {
		this.aventura = aventura;
	}
	
}

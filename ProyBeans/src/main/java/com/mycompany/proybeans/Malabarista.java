package com.mycompany.proybeans;

public class Malabarista implements Participante {

	private int pelotas = 3;
	
	
	public Malabarista() {}
	
	//inyeccion por constructor
	public Malabarista(int pelotas) {
		this.pelotas = pelotas;
	}
	
	@Override
	public void ejecutar() throws EjecutarException {
		System.out.println("trabajar con " + pelotas + " pelotas");

	}

}

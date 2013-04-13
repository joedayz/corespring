package com.mycompany.proybeans;

public class Musico implements Participante {
	private String cancion;
	private Instrumento instrumento;
	
	
	
	@Override
	public void ejecutar() throws EjecutarException {
		System.out.println("Tocar " + cancion + " : ");
		instrumento.tocar();
	}


	
	//inyeccion por setters
	
	public void setCancion(String cancion) {
		this.cancion = cancion;
	}



	public void setInstrumento(Instrumento instrumento) {
		this.instrumento = instrumento;
	}


}

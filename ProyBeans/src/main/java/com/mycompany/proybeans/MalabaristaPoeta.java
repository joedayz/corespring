package com.mycompany.proybeans;

public class MalabaristaPoeta extends Malabarista {

	private Poema poema;
	
	public MalabaristaPoeta(Poema poema){
		super();
		this.poema = poema;
	}
	
	public MalabaristaPoeta( int pelotas, Poema poema){
		super(pelotas);
		this.poema = poema;
	}
	
	public void ejecutar() throws EjecutarException {
		super.ejecutar();
		System.out.println("Damas y caballeros recitare..");
		poema.recitar();
	}
	

}

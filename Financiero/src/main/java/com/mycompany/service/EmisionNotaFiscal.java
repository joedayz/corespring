package com.mycompany.service;

public class EmisionNotaFiscal {

	private Logging logging;
	
	//Inyeccion por constructor
	public EmisionNotaFiscal(Logging logging){
		this.logging = logging;
		
	}
	
	public void emitir(NotaFiscal nf){
		this.logging.registrar("Nota fiscal emitida: "+
					nf.getNumero());
	}
	
}

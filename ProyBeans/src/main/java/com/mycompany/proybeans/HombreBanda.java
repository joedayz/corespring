package com.mycompany.proybeans;

import java.util.Map;



public class HombreBanda implements Participante {

	private Map<String, Instrumento> instrumentos;
	
	
	@Override
	public void ejecutar() throws EjecutarException {
		for(String key: instrumentos.keySet()){
			System.out.println("key es "+ key);
			Instrumento instrumento = instrumentos.get(key);
			instrumento.tocar();
			
		}
	}


	public void setInstrumentos(Map<String, Instrumento> instrumentos) {
		this.instrumentos = instrumentos;
	}
	
	

}

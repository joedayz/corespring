package com.mycompany.proybeans;

public class Etapa {


	private Etapa(){
		
	}
	
	private static class EtapaSingletonHolder {
		static Etapa instancia = new Etapa();
	}
	
	//method factory
	public static Etapa getInstance() {
		return EtapaSingletonHolder.instancia;
	}



	
}

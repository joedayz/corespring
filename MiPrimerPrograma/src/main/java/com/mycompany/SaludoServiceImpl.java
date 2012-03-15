package com.mycompany;

public class SaludoServiceImpl implements SaludoService {

	//atributos
	private String saludo;
	
	//constructores
	public SaludoServiceImpl(){}
	
	public SaludoServiceImpl(String saludo){
		this.saludo = saludo;
	}
	
	//metodos
	public void saludar() {
		System.out.println("Saludo es "+saludo);
	}

}

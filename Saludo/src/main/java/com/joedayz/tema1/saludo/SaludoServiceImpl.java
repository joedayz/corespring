package com.joedayz.tema1.saludo;


public class SaludoServiceImpl implements SaludoService {
	
	private String saludo;
	
	public SaludoServiceImpl() {
		this.saludo = "Hola a todos =)";
	}
	
	public void setSaludo(String saludo) {
		this.saludo = saludo;
	}
	
	public SaludoServiceImpl(String saludo){
		this.saludo = saludo;
	}

	public void saludar() {
		System.out.println(saludo);
	}
	
}

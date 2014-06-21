package com.mycompany.service;

public class ConsoleLogging implements Logging {

	@Override
	public void registrar(String mensaje) {
		System.out.println(mensaje);

	}

}

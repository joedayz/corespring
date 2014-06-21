package com.mycompany.service;

public class NotaFiscal {

	public String getNumero() {
		
		return  String.valueOf(
				(Math.random() * 100  - 1));
	}



}

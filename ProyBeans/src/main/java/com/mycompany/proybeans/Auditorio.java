package com.mycompany.proybeans;

import java.io.Serializable;

public class Auditorio implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3561983820382122630L;

	public void prenderLuces(){
		System.out.println("prender luces");
	}
	
	public void apagarLuces(){
		System.out.println("apagar luces");
	}
}

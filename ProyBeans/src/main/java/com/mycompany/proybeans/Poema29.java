package com.mycompany.proybeans;

public class Poema29 implements Poema {

	private static String[] LINEAS = {
	"LOS POLLITOS DICEN PIO PIO PIO",
	"CUANDO TIENEN HAMBRE",
	"CUANDO TIENEN FRIO"
	};
	
	
	@Override
	public void recitar() {
		for(String linea: LINEAS){
			System.out.println(linea);
		}

	}

}

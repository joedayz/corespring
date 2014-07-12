package gob.sbs.caballeros;

public class Trovador {

	public void cantarAntes(Caballero caballero){
		System.out.println("Suerte Sir " + caballero.getNombre());
	}

	public void cantarDespues(Caballero caballero){
		System.out.println("Bienvenido Sir " + caballero.getNombre());
	}
	
}

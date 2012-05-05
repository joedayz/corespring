package com.joedayz.tema1.caballero;

import org.apache.log4j.Logger;

public class Trovador {
	
	  private static final Logger SONG = 
	      Logger.getLogger(Trovador.class);

	  public Trovador() {}
	  
	  public void cantarAntes(Caballero caballero) {
	    SONG.info("Fa la la; Sir " + caballero.getNombre() +
	        " es tan valiente!");
	  }
	  
	  public void cantarDespues(Caballero caballero) {
	    SONG.info("Tee-hee-he; Sir " + caballero.getNombre() +
	        " termino su aventura!");
	  }

}

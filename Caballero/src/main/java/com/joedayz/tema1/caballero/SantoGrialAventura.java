package com.joedayz.tema1.caballero;

public class SantoGrialAventura implements Aventura{
	  public SantoGrialAventura() {}
	  
	  public Object embarcar() throws GrialNotFoundException {
	    // do whatever it means to embark on a quest
	    return new SantoGrial();
	  }
	
}

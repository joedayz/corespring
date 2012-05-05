package com.joedayz.tema1.caballero;

import junit.framework.TestCase;

public class CaballeroDeLaMesaRedondaTest extends TestCase {

	 public void testEmbarcarEnAventura() throws GrialNotFoundException {
		 
		    CaballeroDeLaMesaRedonda caballero = new CaballeroDeLaMesaRedonda("Amadeo");
		    
		    caballero.setAventura(new SantoGrialAventura());
		    
		    try {
		    	
		      SantoGrial grial = (SantoGrial) caballero.embarcarEnAventura();
		      
		      assertNotNull(grial);
		      
		      assertTrue(grial.isSanto());
		    } catch (AventuraFailedException e) {
		    	
		      fail();
		    }
	 }
}

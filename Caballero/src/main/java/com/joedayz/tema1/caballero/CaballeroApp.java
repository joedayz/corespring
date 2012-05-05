package com.joedayz.tema1.caballero;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CaballeroApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
	    ApplicationContext ctx = new ClassPathXmlApplicationContext("/com/joedayz/tema1/caballero/caballero.xml");

	    Caballero caballero = (Caballero) ctx.getBean("caballero");

	    caballero.embarcarEnAventura();
	}

}

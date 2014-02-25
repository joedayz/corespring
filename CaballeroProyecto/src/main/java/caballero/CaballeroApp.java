package caballero;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CaballeroApp {

	public static void main(String[] args) throws Exception {
		
	    ApplicationContext ctx = new 
	    		ClassPathXmlApplicationContext("caballero.xml");

	    Caballero caballero = (Caballero) ctx.getBean("caballero");

	    caballero.embarcarEnAventura();
	}
}

package com.mycompany.proybeans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class PeruTieneTalento {

	/**
	 * @param args
	 * @throws EjecutarException 
	 */
	public static void main(String[] args) throws EjecutarException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("peru-tiene-talento.xml");
		//I = C
		Participante participante =   (Participante) ctx.getBean("johnnyPoeta");
		
		participante.ejecutar();
		
		// SPRING  por defecto sus beans son singletons.
		
		Etapa etapa1 = (Etapa) ctx.getBean("etapa");
		System.out.println(etapa1);
		
		Etapa etapa2 = (Etapa) ctx.getBean("etapa");
		System.out.println(etapa2);
		
		//prototype
		Ticket ticket1 = (Ticket) ctx.getBean("ticket");
		System.out.println(ticket1);
		Ticket ticket2 = (Ticket) ctx.getBean("ticket");
		System.out.println(ticket2);
		
		// request, session, global-session
		HombreBanda banda = (HombreBanda) ctx.getBean("deborah");
		banda.ejecutar();
		
	}

}

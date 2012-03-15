package com.mycompany;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;


public class SaludoApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BeanFactory 
		   fabrica = new XmlBeanFactory(new 
				   ClassPathResource("saludo.xml"));
		
		//  I  = C1, c2, c3, c4, 
		SaludoService servicio = 
				 (SaludoService)fabrica.getBean("saludoService");
		
		servicio.saludar();

	}

}

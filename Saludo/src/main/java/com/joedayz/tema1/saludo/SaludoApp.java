package com.joedayz.tema1.saludo;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class SaludoApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BeanFactory factory = new XmlBeanFactory(new ClassPathResource("saludo.xml"));

	    SaludoService saludoService = (SaludoService) factory.getBean("saludoService");

	    saludoService.saludar();

	}

}

package saludo;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SaludoApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		BeanFactory fabrica = 
				new ClassPathXmlApplicationContext("saludo.xml");
		SaludoService servicio  = 
				(SaludoService) fabrica.getBean("saludoService");
		
		servicio.saludar();
	}

}

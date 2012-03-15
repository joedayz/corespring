package caballero;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class CaballeroApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BeanFactory fabrica =
				new XmlBeanFactory(new 
						ClassPathResource("caballero.xml"));
		
		Caballero caballero = 
				(Caballero) fabrica.getBean("caballero");
		
		SantoGrial grial = (SantoGrial)caballero.embarcarEnAventura();
		System.out.println("si retorno el santo grial =" + grial);
		
		

	}

}

package pe.joedayz.dhlebj2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class FrontDeskMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-front.xml");

        FrontDesk frontDesk = (FrontDesk) context.getBean("frontDesk");
        double postage = frontDesk.calculatePostage("US", 1.5);
        System.out.println(postage);
    }
}

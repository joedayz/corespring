package rewards.remoting;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RmiExporterBootstrap {

	public static void main(String[] args) throws Exception {
		new ClassPathXmlApplicationContext(new String[] { "rewards/remoting/rmi-server-config.xml",
				"rewards/system-test-config.xml" });
		System.out.println("RMI reward network server started.");
		System.in.read();
	}
}

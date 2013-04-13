package com.mycompany.proybeans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Estadio implements InitializingBean, DisposableBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("abriendo estadio");
	}

	@Override
	public void destroy() throws Exception {
		
		System.out.println("cerrando estadio");
	}

}

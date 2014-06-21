package com.mycompany.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProductor {

	private EntityManagerFactory factory;
	
	public EntityManagerProductor (){
		this.factory =Persistence.
	createEntityManagerFactory("FinancieroPU");
	}
	
	@Produces
	@RequestScoped
	public EntityManager createEntityManager(){
		return factory.createEntityManager();
	}
	
	public void closeEntitManager(@Disposes 
			EntityManager manager){
		manager.close();
	}
	
	
	
	
	
	
}

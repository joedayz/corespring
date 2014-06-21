package com.mycompany.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.mycompany.model.Movimiento;

public class MovimientoRepository {

	private EntityManager manager;
	
	@Inject
	public MovimientoRepository(EntityManager manager){
		this.manager = manager;
	}
	
	public List<Movimiento> todos(){
		TypedQuery<Movimiento> query = 
				manager.createQuery("from Movimiento",
						Movimiento.class);
		return query.getResultList();
	}
	
	public void agregar(Movimiento movimiento){
		EntityTransaction trx = this.manager.getTransaction();
		trx.begin();
		this.manager.persist(movimiento);
		trx.commit();
	}
	
	
	
	
	
	
}

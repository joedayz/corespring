package com.mycompany.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.mycompany.model.Movimiento;

public class MovimientoRepository {

	private EntityManager manager;
	
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
		this.manager.persist(movimiento);
	}
	
	
	
	
	
	
}

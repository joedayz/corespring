package com.mycompany.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.mycompany.model.Persona;

public class PersonaRepository {

	private EntityManager manager;
	
	public PersonaRepository(EntityManager manager){
		this.manager = manager;
	}
	
	public Persona porId(Long id){
		return manager.find(Persona.class, id);
	}
	public List<Persona> todas(){
		TypedQuery<Persona> query = manager.createQuery(
				"from Persona", Persona.class);
		return query.getResultList();
	}
	
}

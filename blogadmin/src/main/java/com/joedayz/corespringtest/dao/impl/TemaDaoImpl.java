package com.joedayz.corespringtest.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.joedayz.corespringtest.dao.TemaDao;
import com.joedayz.corespringtest.domain.Tema;

@Repository
public class TemaDaoImpl implements TemaDao {
	
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	public TemaDaoImpl(SessionFactory sessionFactory){
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public List<Tema> listarTodosLosTemas() {
		
		StringBuilder hql = new StringBuilder("from Tema");
		List<Tema> temas = hibernateTemplate.find(hql.toString());
		return temas;
	}

	public Tema obtenerTema(Long id) {
		return hibernateTemplate.get(Tema.class, id);
	}

}

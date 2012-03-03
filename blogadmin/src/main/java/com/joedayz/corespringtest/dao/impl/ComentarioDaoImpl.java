package com.joedayz.corespringtest.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.joedayz.corespringtest.dao.ComentarioDao;
import com.joedayz.corespringtest.domain.Comentario;

@Repository
public class ComentarioDaoImpl implements ComentarioDao {
	
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	public ComentarioDaoImpl(SessionFactory sessionFactory){
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public List<Comentario> listarTodosLosComentariosPorPost(Long id) {
		
		StringBuilder hql = new StringBuilder("from Comentario where idPost = ? order by id desc");
		List<Comentario> comentarios = hibernateTemplate.find(hql.toString(), id);
		return comentarios;
	}
	
	public void guardarComentario(Comentario comentario){
		
		hibernateTemplate.saveOrUpdate(comentario);
		hibernateTemplate.flush();		
	}

}

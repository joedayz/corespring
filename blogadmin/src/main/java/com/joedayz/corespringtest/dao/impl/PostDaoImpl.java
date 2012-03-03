package com.joedayz.corespringtest.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.joedayz.corespringtest.dao.PostDao;
import com.joedayz.corespringtest.domain.Post;

@Repository
public class PostDaoImpl implements PostDao {
	
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	public PostDaoImpl(SessionFactory sessionFactory){
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public List<Post> listarTodosLosPostPorTema(Long id) {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Post.class);
		criteria.add(Restrictions.eq("tema.id", id));
		List<Post> posts = hibernateTemplate.findByCriteria(criteria);		
		return posts;
	}
	
	public void guardarPost(Post post){
	
		hibernateTemplate.saveOrUpdate(post);
		hibernateTemplate.flush();
	}

	public Post obtenerPost(Long id) {
		return hibernateTemplate.get(Post.class, id);
	}
}

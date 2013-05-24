package br.com.itexto.springforum.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.itexto.springforum.dao.DAOPermisoUsuario;
import br.com.itexto.springforum.entidades.PermisoUsuario;
import br.com.itexto.springforum.entidades.Usuario;

@Repository("daoPermisoUsuario")
public class HBPermisoUsuario implements DAOPermisoUsuario {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<PermisoUsuario> getPermisoUsuario(Usuario usuario) {
		if (usuario == null) {
			return new ArrayList<PermisoUsuario>();
		}
		Query query = sessionFactory.getCurrentSession().createQuery("from PermisoUsuario pu where pu.usuario = ?");
		query.setEntity(0, usuario);
		return query.list();
	}

	public void addRole(String role, Usuario usuario) {
		if (role != null && usuario != null) {
			PermisoUsuario permissao = new PermisoUsuario();
			permissao.setRole(role);
			permissao.setUsuario(usuario);
			sessionFactory.getCurrentSession().saveOrUpdate(permissao);
		}
		
	}

}

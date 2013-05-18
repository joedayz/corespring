package br.com.itexto.springforum.dao.hibernate;




import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.com.itexto.springforum.dao.DAOUsuario;
import br.com.itexto.springforum.entidades.Usuario;

@Repository
public class HBUsuario extends HBDAO<Usuario> implements DAOUsuario {

	@Override
	protected Class getClazz() {
		return Usuario.class;
	}

	public Usuario getUsuario(String login, String password) {
		Query query = getSession().createQuery("from Usuario usr where usr.login = ? and " +
					" usr.hashPassword = ?");
		query.setString(0, login);
		query.setString(1, DigestUtils.sha256Hex(password));
		return (Usuario) query.uniqueResult();
	}

	public Usuario getUsuario(String login) {
		Query query = getSession().createQuery("from Usuario usr where usr.login = ?");
		query.setString(0, login);
		return (Usuario) query.uniqueResult();
	}


}

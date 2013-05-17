package br.com.itexto.springforum.dao.hibernate;




import org.springframework.stereotype.Repository;

import br.com.itexto.springforum.dao.DAOUsuario;
import br.com.itexto.springforum.entidades.Usuario;

@Repository
public class HBUsuario extends HBDAO<Usuario> implements DAOUsuario {

	@Override
	protected Class getClazz() {
		return Usuario.class;
	}



}

package br.com.itexto.springforum.dao.hibernate;

import org.springframework.stereotype.Repository;

import br.com.itexto.springforum.dao.DAOTema;
import br.com.itexto.springforum.entidades.Tema;

@Repository
public class HBTema extends HBDAO<Tema> implements DAOTema {

	@Override
	protected Class getClazz() {
		
		return Tema.class;
	}

	

}

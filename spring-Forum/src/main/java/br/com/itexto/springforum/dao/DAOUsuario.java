package br.com.itexto.springforum.dao;

import br.com.itexto.springforum.entidades.Usuario;

public interface DAOUsuario extends DAOBase<Usuario>{

	public Usuario getUsuario(String login, String password);

	public Usuario getUsuario(String login);

}

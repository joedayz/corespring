package br.com.itexto.springforum.dao;

import java.util.List;

import br.com.itexto.springforum.entidades.PermisoUsuario;
import br.com.itexto.springforum.entidades.Usuario;

public interface DAOPermisoUsuario {
	
	public List<PermisoUsuario> getPermisoUsuario(Usuario usuario);
	
	public void addRole(String role, Usuario usuario);
	
}

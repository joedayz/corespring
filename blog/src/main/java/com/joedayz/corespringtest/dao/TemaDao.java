package com.joedayz.corespringtest.dao;

import java.util.List;

import com.joedayz.corespringtest.domain.Tema;

public interface TemaDao {

	public List<Tema> listarTodosLosTemas();

	public Tema obtenerTema(Long id) ;
}

package com.joedayz.corespringtest.service;

import java.util.List;

import com.joedayz.corespringtest.domain.Tema;

public interface TemaService {

	public List<Tema> listarTodosLosTemas();
	
	public Tema obtenerTema(Long id);

}

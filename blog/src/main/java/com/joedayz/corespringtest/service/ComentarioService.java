package com.joedayz.corespringtest.service;

import java.util.List;

import com.joedayz.corespringtest.domain.Comentario;

public interface ComentarioService {

	public List<Comentario> listarTodosLosComentariosPorPost(Long id);
	
	public void guardarComentario(Comentario comentario);
}

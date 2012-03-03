package com.joedayz.corespringtest.dao;

import java.util.List;

import com.joedayz.corespringtest.domain.Comentario;

public interface ComentarioDao {

	public List<Comentario> listarTodosLosComentariosPorPost(Long id);
	
	public void guardarComentario(Comentario comentario);
}

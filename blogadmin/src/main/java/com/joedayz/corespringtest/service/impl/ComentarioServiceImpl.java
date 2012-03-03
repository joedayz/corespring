package com.joedayz.corespringtest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joedayz.corespringtest.dao.ComentarioDao;
import com.joedayz.corespringtest.domain.Comentario;
import com.joedayz.corespringtest.service.ComentarioService;

@Service
public class ComentarioServiceImpl implements ComentarioService {
	
	@Autowired
	private ComentarioDao comentarioDao;
	
	public List<Comentario> listarTodosLosComentariosPorPost(Long id) {
		return comentarioDao.listarTodosLosComentariosPorPost(id);
	}

	public void guardarComentario(Comentario comentario) {

		comentarioDao.guardarComentario(comentario);
	}

}

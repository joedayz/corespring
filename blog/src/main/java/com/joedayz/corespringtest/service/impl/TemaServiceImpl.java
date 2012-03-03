package com.joedayz.corespringtest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joedayz.corespringtest.dao.TemaDao;
import com.joedayz.corespringtest.domain.Tema;
import com.joedayz.corespringtest.service.TemaService;

@Service
public class TemaServiceImpl implements TemaService {
	
	@Autowired
	private TemaDao temaDao;

	public List<Tema> listarTodosLosTemas() {
		
		return temaDao.listarTodosLosTemas();
	}

	public Tema obtenerTema(Long id) {
		
		return temaDao.obtenerTema(id);
	}

}

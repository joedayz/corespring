package com.joedayz.corespringtest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joedayz.corespringtest.dao.PostDao;
import com.joedayz.corespringtest.domain.Post;
import com.joedayz.corespringtest.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDao postDao;
	
	public List<Post> listarTodosLosPostPorTema(Long id) {
		return postDao.listarTodosLosPostPorTema(id);
	}

	public void guardarPost(Post post) {

		postDao.guardarPost(post);		
	}

	public Post obtenerPost(Long id) {
		return postDao.obtenerPost(id);
	}

}

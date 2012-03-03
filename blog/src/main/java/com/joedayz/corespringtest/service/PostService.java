package com.joedayz.corespringtest.service;

import java.util.List;

import com.joedayz.corespringtest.domain.Post;

public interface PostService {

	public List<Post> listarTodosLosPostPorTema(Long id);
	
	public void guardarPost(Post post);
	
	public Post obtenerPost(Long id);
}

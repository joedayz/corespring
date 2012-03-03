package com.joedayz.corespringtest.dao;

import java.util.List;

import com.joedayz.corespringtest.domain.Post;

public interface PostDao {
	
	public List<Post> listarTodosLosPostPorTema(Long id);
	
	public void guardarPost(Post post);
	
	public Post obtenerPost(Long id);

}

package com.joedayz.corespringtest.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.joedayz.corespringtest.domain.Post;

import static org.junit.Assert.*;

@ContextConfiguration(locations={"classpath:com/joedayz/corespringtest/system-test-config.xml"})
public class PostDaoTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private PostDao postDao;
	
	@Test
	public void listarTodosLosPostPorTema(){
		
		Long id = new Long(3);
		List<Post> posts = postDao.listarTodosLosPostPorTema(id);
		assertNotNull(posts);
		System.out.println(posts);
	}
}

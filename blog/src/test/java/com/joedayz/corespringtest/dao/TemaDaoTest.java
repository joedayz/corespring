package com.joedayz.corespringtest.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.joedayz.corespringtest.domain.Tema;



@ContextConfiguration(locations={"classpath:com/joedayz/corespringtest/system-test-config.xml"})
public class TemaDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private TemaDao temaDao;
	
	@Test
	public void listarTemas(){
		
		List<Tema> temas = temaDao.listarTodosLosTemas();
		assertNotNull(temas);
	}
}

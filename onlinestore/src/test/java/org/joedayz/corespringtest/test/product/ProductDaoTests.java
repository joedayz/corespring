package org.joedayz.corespringtest.test.product;


import java.util.List;

import org.joedayz.corespringtest.dao.ProductDao;
import org.joedayz.corespringtest.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:org/joedayz/corespringtest/test/system-test-config.xml"})
public class ProductDaoTests {

	@Autowired
	private ProductDao productDao;
	
	@Test
	public void testGetProductsList(){
		
		List<Product> list = productDao.getProductsList(null);		
		assertNotNull(list);
	}
	
	@Test
	public void testIsRepeatDescription(){
		
		boolean isRepeatDescription = productDao.isRepeatDescription("  iPad Touch 32Gb   ", new Integer(1));
		assertEquals(false, isRepeatDescription);
	}
	
}

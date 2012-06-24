package org.joedayz.corespringtest.service.impl;

import java.util.List;

import org.joedayz.corespringtest.dao.ProductDao;
import org.joedayz.corespringtest.model.Category;
import org.joedayz.corespringtest.model.Product;
import org.joedayz.corespringtest.service.ProductManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductManagerImpl implements ProductManager {

	@Autowired
	private ProductDao productDao;
	
	public void deleteProduct(Integer idProduct) {
		productDao.deleteProduct(idProduct);
	}

	public Product getProductById(Integer idProduct) {
		return productDao.getProductById(idProduct);
	}

	public List<Product> getProductsList(Product product) {
		return productDao.getProductsList(product);
	}

	public void saveProduct(Product product) {
		productDao.saveProduct(product);
	}

	public List<Category> getCategoriesTypesList() {
		return productDao.getCategoriesTypesList();
	}

	public boolean isRepeatDescription(String name, Integer idProduct) {
		return productDao.isRepeatDescription(name, idProduct);
	}

}

package org.joedayz.corespringtest.service;

import java.util.List;

import org.joedayz.corespringtest.model.Category;
import org.joedayz.corespringtest.model.Product;

public interface ProductManager {
	
	public List<Product> getProductsList(Product product);
	public Product getProductById(Integer idProduct);
	public void saveProduct(Product product);
	public void deleteProduct(Integer idProduct);
	
	public List<Category> getCategoriesTypesList();
	public boolean isRepeatDescription(String name, Integer idProduct);
}

package org.joedayz.corespringtest.model;

import java.math.BigDecimal;

public class Product implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7544979582447592770L;
	private Integer idProduct;
	private Category category = new Category();
	private String name;
	private String code;
	private BigDecimal price;	
	private String description;

	public Product() {
	}

	public Product(Category category, String name, String code,
			BigDecimal price) {
		this.category = category;
		this.name = name;
		this.code = code;
		this.price = price;
	}

	public Integer getIdProduct() {
		return this.idProduct;
	}

	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

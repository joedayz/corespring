package org.joedayz.corespringtest.model;

public class Category implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9053718606509360894L;
	private Integer idCategory;
	private String name;

	public Category() {
	}

	public Category(String name) {
		this.name = name;
	}

	public Integer getIdCategory() {
		return this.idCategory;
	}

	public void setIdCategory(Integer idCategory) {
		this.idCategory = idCategory;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

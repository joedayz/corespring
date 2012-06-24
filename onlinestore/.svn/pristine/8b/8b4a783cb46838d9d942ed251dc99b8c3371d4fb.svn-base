package org.joedayz.corespringtest.web.validator;

import org.apache.commons.lang.StringUtils;
import org.joedayz.corespringtest.model.Product;
import org.joedayz.corespringtest.service.ProductManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator{

	@Autowired
	private ProductManager productManager;
		
	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	public void validate(Object object, Errors errors) {
		
		Product product = (Product)object;
		if (StringUtils.isEmpty(product.getName()))
		{
			errors.rejectValue("name", "product.name.required");
		}
		
		if(product.getPrice() == null
				|| StringUtils.isEmpty(product.getPrice().toString()))
		{
			errors.rejectValue("price", "product.price.required");		
		}
		
		if (StringUtils.isEmpty(product.getCode()))
		{
			errors.rejectValue("code", "product.code.required");
		}
		
		boolean isRepeatName = productManager.isRepeatDescription(product.getName(), product.getIdProduct());
		if(isRepeatName){
			errors.rejectValue("name", "product.name.isRepeat");		
		}
	}

}

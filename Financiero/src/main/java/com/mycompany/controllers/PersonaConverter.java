package com.mycompany.controllers;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import com.mycompany.model.Persona;
import com.mycompany.repository.PersonaRepository;
import com.mycompany.util.JpaUtil;

@FacesConverter(forClass = Persona.class)
public class PersonaConverter 
	implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Persona retorno = null;
		EntityManager manager = JpaUtil.getEntityManager();
		
		try{
			if (value!=null){
				PersonaRepository repository =
						new PersonaRepository(manager);
				retorno = repository.porId(new Long(value));
			}
			return retorno;
		}finally{
			manager.close();
		}
	
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value!=null)
			return ((Persona) value).getId().toString();
		return null;
	}

}

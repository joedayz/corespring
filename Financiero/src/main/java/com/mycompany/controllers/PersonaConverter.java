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
public class PersonaConverter implements Converter {

	// @Inject no es soportado en un Converter
	private PersonaRepository personaRepository;

	public PersonaConverter() {
		personaRepository = CDILocator.getBean(PersonaRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Persona retorno = null;

		if (value != null) {
			retorno = personaRepository.porId(new Long(value));
		}
		return retorno;

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null)
			return ((Persona) value).getId().toString();
		return null;
	}

}

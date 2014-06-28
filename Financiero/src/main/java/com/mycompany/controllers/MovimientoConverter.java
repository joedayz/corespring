package com.mycompany.controllers;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.mycompany.model.Movimiento;
import com.mycompany.repository.MovimientoRepository;

@FacesConverter(forClass = Movimiento.class)
public class MovimientoConverter implements 
	Converter{

	
	private MovimientoRepository repository;

	public MovimientoConverter(){
		 this.repository = 
				 CDILocator.
				 getBean(MovimientoRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Movimiento retorno = null;
		if (value!=null){
			retorno = this.repository.porId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value!=null){
			Movimiento movimiento = ((Movimiento) value);
			return movimiento.getId() == null ? null:
				movimiento.getId().toString();
		}
		return null;
	}
	
	
	
}


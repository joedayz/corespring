package com.mycompany.controllers;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.mycompany.model.Movimiento;

@FacesConverter(forClass = Movimiento.class)
public class MovimientoConverter implements 
	Converter{

	
	private Movimiento movimientos;

	public MovimientoConverter(){
		 
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}


package com.mycompany.service;

import java.util.Date;

import javax.inject.Inject;

import com.mycompany.model.Movimiento;
import com.mycompany.repository.MovimientoRepository;

public class RegistroMovimientos {

	@Inject
	private MovimientoRepository repository;
	
	
	public void guardar(Movimiento movimiento) 
			throws FinancieroException{
		if(movimiento.getFechaPago()!=null &&
				movimiento.getFechaPago().after(new Date())){
			throw 
			new FinancieroException("Fecha de pago no puede"
					+ "ser una fecha futura");
		}
		this.repository.agregar(movimiento);
	}
}

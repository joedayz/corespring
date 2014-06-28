package com.mycompany.service;

import java.util.Date;

import javax.inject.Inject;

import com.mycompany.interceptor.Transactional;
import com.mycompany.model.Movimiento;
import com.mycompany.repository.MovimientoRepository;

public class RegistroMovimientos {

	@Inject
	private MovimientoRepository repository;
	
	@Transactional
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
	
	@Transactional
	public void eliminar(Movimiento movimiento) 
			throws FinancieroException {
		movimiento = this.repository.
				porId(movimiento.getId());
		if(movimiento.getFechaPago()!=null){
			throw new FinancieroException(
			"No es posible eliminar un movimiento pagado!");
		}
		this.repository.eliminar(movimiento);
	}
	
	
}

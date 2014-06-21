package com.mycompany.controllers;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import com.mycompany.model.Movimiento;
import com.mycompany.repository.MovimientoRepository;
import com.mycompany.util.JpaUtil;

@Named
@ViewScoped
public class ConsultaMovimientosBean {

	@Inject 
	private MovimientoRepository movimientoRepository;
	
	private List<Movimiento> movimientos;
	
	public void consultar() {

		this.movimientos = movimientoRepository.todos();
	
	}

	public List<Movimiento> getMovimientos() {
		return movimientos;
	}
	
	
	
}

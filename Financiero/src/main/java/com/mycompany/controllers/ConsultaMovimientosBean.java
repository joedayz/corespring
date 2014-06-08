package com.mycompany.controllers;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.mycompany.model.Movimiento;
import com.mycompany.repository.MovimientoRepository;
import com.mycompany.util.JpaUtil;

@ManagedBean
@ViewScoped
public class ConsultaMovimientosBean {

	private List<Movimiento> movimientos;
	
	public void consultar() {
		EntityManager manager = JpaUtil.getEntityManager();
		MovimientoRepository repository =
				new MovimientoRepository(manager);
		this.movimientos = repository.todos();
		manager.close();
	}

	public List<Movimiento> getMovimientos() {
		return movimientos;
	}
	
	
	
}

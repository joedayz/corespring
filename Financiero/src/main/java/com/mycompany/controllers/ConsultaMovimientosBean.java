package com.mycompany.controllers;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mycompany.model.Movimiento;
import com.mycompany.repository.MovimientoRepository;
import com.mycompany.service.FinancieroException;
import com.mycompany.service.RegistroMovimientos;

@Named
@ViewScoped
public class ConsultaMovimientosBean {

	@Inject
	private MovimientoRepository movimientoRepository;
	@Inject 
	private RegistroMovimientos registroMovimientos;
	
	private List<Movimiento> movimientos;
	
	private Movimiento movimientoSeleccionado;
	
	public void eliminar() {
		FacesContext context =
				FacesContext.getCurrentInstance();
		try{
			this.registroMovimientos.eliminar(
					this.movimientoSeleccionado);
			this.consultar();
			context.addMessage(null,
					new FacesMessage("Movimiento "
							+ "eliminado "
							+ "con exito!"));
		}catch(FinancieroException ex){
			FacesMessage mensaje = 
					new FacesMessage(ex.getMessage());
			mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensaje);
		}
	}
	
	
	public void consultar() {

		this.movimientos = movimientoRepository.todos();
	
	}

	public List<Movimiento> getMovimientos() {
		return movimientos;
	}


	public Movimiento getMovimientoSeleccionado() {
		return movimientoSeleccionado;
	}


	public void setMovimientoSeleccionado(Movimiento movimientoSeleccionado) {
		this.movimientoSeleccionado = movimientoSeleccionado;
	}
	
	
	
}

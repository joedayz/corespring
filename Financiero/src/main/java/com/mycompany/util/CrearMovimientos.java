package com.mycompany.util;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.mycompany.model.Movimiento;
import com.mycompany.model.Persona;
import com.mycompany.model.TipoMovimiento;

public class CrearMovimientos {

	public static void main(String[] args) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx =
				manager.getTransaction();
		trx.begin();
		
		Calendar fechaVencimiento1 = Calendar.getInstance();
		fechaVencimiento1.set(2014,10,1,0,0,0);
		Calendar fechaVencimiento2 = Calendar.getInstance();
		fechaVencimiento2.set(2014,12,10,0,0,0);
		Persona cliente = new Persona();
		cliente.setNombre("Inversiones Ochoa");
		Persona proveedor = new Persona();
		proveedor.setNombre("Frenosa");
		Movimiento movimiento1 = new Movimiento();
		movimiento1.setDescripcion("Venta de acero 3 pulg.");
		movimiento1.setPersona(cliente);
		movimiento1.setFechaVencimiento(fechaVencimiento1.getTime());
		movimiento1.setFechaPago(fechaVencimiento1.getTime());
		movimiento1.setValor(new BigDecimal(103_000));
		movimiento1.setTipo(TipoMovimiento.INGRESO);
		
		Movimiento movimiento2 = new Movimiento();
		movimiento2.setDescripcion("Liquido de freno.");
		movimiento2.setPersona(proveedor);
		movimiento2.setFechaVencimiento(fechaVencimiento2.getTime());
		movimiento2.setFechaPago(fechaVencimiento2.getTime());
		movimiento2.setValor(new BigDecimal(68_000));
		movimiento2.setTipo(TipoMovimiento.SALIDA);		
		
		manager.persist(cliente);
		manager.persist(proveedor);
		manager.persist(movimiento1);
		manager.persist(movimiento2);
		
		trx.commit();
		manager.close();

	}

}

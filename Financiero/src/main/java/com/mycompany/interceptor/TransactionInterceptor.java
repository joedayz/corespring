package com.mycompany.interceptor;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


@Interceptor
@Transactional
public class TransactionInterceptor 
					implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private @Inject EntityManager manager;
	
	@AroundInvoke
	public Object invoke(InvocationContext context)
				throws Exception {
		EntityTransaction trx = manager.getTransaction();
		boolean creado = false;
		try{
			if(!trx.isActive()){
				//truco para hacer rollback
				//a lo que ya haya sucedido
				//y haya operaciones sin transaccion
				trx.begin();
				trx.rollback();

				//ahora si inicia la transaction
				trx.begin();
				creado = true;
			}
			return context.proceed();
		}catch (Exception ex){
			if(trx!=null && creado){
				trx.rollback();
			}
			throw ex;
		} finally{
			if(trx!=null && trx.isActive() && creado){
				trx.commit();
			}
		}
	
	}
	

}

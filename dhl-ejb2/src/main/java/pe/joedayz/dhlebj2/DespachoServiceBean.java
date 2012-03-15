package pe.joedayz.dhlebj2;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class DespachoServiceBean implements SessionBean, DespachoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3447868349414980418L;
	private DespachoService despachoService;
	@SuppressWarnings("unused")
	private SessionContext sessionContext;
	// this isn't part of the interface, but is required	
	public void ejbCreate() {
		despachoService = new DespachoServiceImpl();
	}
	

	@Override
	public void ejbActivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ejbRemove() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}
	@Override
	public double calcularDespacho(String pais, double peso) {
		return despachoService.calcularDespacho(pais, peso);
	}
}

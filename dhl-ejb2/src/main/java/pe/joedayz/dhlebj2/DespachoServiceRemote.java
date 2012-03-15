package pe.joedayz.dhlebj2;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

public interface DespachoServiceRemote extends EJBObject {
	public double calcularDespacho(String pais, double peso) throws RemoteException;
}

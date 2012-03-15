package pe.joedayz.dhlebj2;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface DespachoServiceHome extends EJBHome {
	public DespachoServiceRemote create() throws RemoteException, CreateException;
}

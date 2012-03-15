package pe.joedayz.dhlebj2;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;


public interface PostageServiceRemote extends EJBObject {
    public double calculatePostage(String country, double weight)
        throws RemoteException;
}

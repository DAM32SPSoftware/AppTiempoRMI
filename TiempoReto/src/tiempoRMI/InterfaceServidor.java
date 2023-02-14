package tiempoRMI;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceServidor extends Remote{
	String mandarURL(String url) throws RemoteException;
}

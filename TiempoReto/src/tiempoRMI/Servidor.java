package tiempoRMI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Servidor extends UnicastRemoteObject implements InterfaceServidor {
	
	private static final long serialVersionUID = 1L;

	protected Servidor() throws RemoteException {
		super();
	}

	public String mandarURL(String url) throws RemoteException {
		try {
			URL u = new URL(url);
			
			URLConnection uc = u.openConnection();

			String ct = uc.getContentType();
			int c1 = uc.getContentLength();
			
			// https://pastebin.com/raw/0pKQDgRn
			if (ct.startsWith("text/") || c1 == -1) {
				BufferedReader in = new BufferedReader(new InputStreamReader(u.openStream()));
				String linea;
				String linea2 = null;
				while ((linea = in.readLine()) != null) {
					// Devuelve el HTML
					linea2 = linea;
					// System.out.println(linea);
				}
				return linea2;
			} else {
				return "";
			}		
		} catch (Exception e) {
			//System.out.println("Error: " + e.getMessage());
			return "";
		}
		
	}

	public static void main(String[] args) {

		try {
			Servidor obj = new Servidor();
			LocateRegistry.createRegistry(1099);
			Naming.rebind("rmi://localhost/Objeto", obj);
			System.out.println("Servidor iniciado...");
		} catch (RemoteException e) {
			System.out.println("Error en el Servidor");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			System.out.println("No encuentro al Servidor");
			e.printStackTrace();
		}

	}

}

package ie.gmit.sw;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StringService extends Remote{
	public Resultator compare(String s, String t, String algo) throws RemoteException;
}
package ie.gmit.sw;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StringCompareService extends Remote{

	public Message getMessage() throws RemoteException;
	public int getScore(String s1, String s2) throws RemoteException;
}
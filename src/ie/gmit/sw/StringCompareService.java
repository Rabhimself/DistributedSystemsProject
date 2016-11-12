package ie.gmit.sw;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StringCompareService extends Remote{
	public int getScore(String s1, String s2, String algo) throws RemoteException;

}
package ie.gmit.sw.stringcompare;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Resultator extends Remote{
	public String getResult() throws RemoteException;
	public void setResult(String result) throws RemoteException;
	public boolean isProcessed() throws RemoteException;
	public void setProcessed() throws RemoteException;
}

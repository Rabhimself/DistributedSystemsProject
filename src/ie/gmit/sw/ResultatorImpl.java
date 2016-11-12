package ie.gmit.sw;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ResultatorImpl extends UnicastRemoteObject implements Resultator {
	private static final long serialVersionUID = 7602852482492224647L;

	private boolean processed;
	private String result;

	protected ResultatorImpl() throws RemoteException {
		super();
	}

	@Override
	public String getResult() throws RemoteException {
		return result;
	}

	@Override
	public void setResult(String result) throws RemoteException {
		this.result = result;

	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed() {
		this.processed = true;
	}

}

package ie.gmit.sw.servlet;

import java.rmi.RemoteException;
import java.util.Map;

import ie.gmit.sw.stringcompare.Resultator;
import ie.gmit.sw.stringcompare.StringService;

//Used by the RequestExecutor, these are the RMI worker threads
public class RequestWorker implements Runnable {

	private Request r;
	private StringService scs = ServiceResolver.getService();
	private Map<String, Resultator> out;
 
    public RequestWorker(Request r, Map<String, Resultator> out) {
        this.r = r;
        this.out = out;
    }
   
	@Override
	public void run() {
		Resultator res = null;
		try {
			//Pass everything off to the stringservice and store the resultator in the map
			res = scs.compare(r.getS1(), r.getS2(), r.getAlgo());
			out.put(r.getTaskNumber(), res);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
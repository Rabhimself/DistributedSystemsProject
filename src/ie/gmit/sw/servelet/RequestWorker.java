package ie.gmit.sw.servelet;

import java.rmi.RemoteException;
import java.util.Map;

import ie.gmit.sw.stringcompare.Resultator;
import ie.gmit.sw.stringcompare.StringService;

public class RequestWorker implements Runnable {

	private Request r;
	private StringService scs;
	private Map<String, Resultator> out;
 
    public RequestWorker(Request r, StringService scs, Map<String, Resultator> out) {
        this.r = r;
        this.scs = scs;
        this.out = out;
    }
   
	@Override
	public void run() {
		Resultator res = null;
		try {
			res = scs.compare(r.getS1(), r.getS2(), r.getAlgo());
			out.put(r.getTaskNumber(), res);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
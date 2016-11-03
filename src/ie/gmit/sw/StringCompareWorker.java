package ie.gmit.sw;

import java.rmi.RemoteException;
import java.util.Map;

public class StringCompareWorker implements Runnable {

	private Job j;
	private StringCompareService scs;
	private Map<String, Job> m;
 
    public StringCompareWorker(Job j, StringCompareService scs, Map<String, Job> m) {
        this.j = j;
        this.scs = scs;
        this.m = m;
    }
   

	@Override
	public void run() {
		Integer score = null;
		try {
			score = scs.getScore(j.getS1(), j.getS2());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		j.setScore(score);
		m.put(j.getTaskNumber(), j);	
	}
}
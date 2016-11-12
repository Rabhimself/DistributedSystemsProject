package ie.gmit.sw;

import java.rmi.RemoteException;

public class StringCompareWorker implements Runnable {

	private Job j;
	private StringCompareService scs;
 
    public StringCompareWorker(Job j, StringCompareService scs) {
        this.j = j;
        this.scs = scs;
    }
   
	@Override
	public void run() {
		Integer score = null;
		try {
			score = scs.getScore(j.getS1(), j.getS2(), j.getAlgo());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		///////////////////////////////////////////////
		//Pretend it's about to do some heavy lifting//
		///////////////////////////////////////////////
		try {
			Thread.sleep(12000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//////////////////////////////////////////////
		
		j.setScore(score);
		j.setComplete(true);	
	}
}
package ie.gmit.sw.stringcompare;

import java.rmi.RemoteException;

//Runnable implementation that calls the actual .distance() method of the algorithm passed to it
//calls thread.sleep() to simulate a log process time.
public class AlgorithmWorker implements Runnable {
	
	private Resultator r;
	private StringComparable algo;
	private String s1;
	private String s2;

	public AlgorithmWorker(Resultator r, StringComparable algo, String s1, String s2) {
		this.r = r;
		this.algo = algo;
		this.s1 = s1;
		this.s2 = s2;
	}

	@Override
	public void run() {
		try {
			try {
				//simulate a long processing time
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//set the result and flip it to processed
			r.setResult(algo.distance(s1, s2));
			r.setProcessed();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

}

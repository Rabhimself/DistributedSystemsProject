package ie.gmit.sw.stringcompare;

import java.rmi.RemoteException;

public class AlgorithmWorker implements Runnable {

	Resultator r;
	StringComparable algo;
	String s1;
	String s2;

	public AlgorithmWorker(Resultator r, StringComparable algo, String s1, String s2) {
		this.r = r;
		this.algo = algo;
		this.s1 = s1;
		this.s2 = s2;
	}

	@Override
	public void run() {
		try {
			r.setResult(algo.distance(s1, s2));
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			r.setProcessed();
			System.out.println(r + "is processed");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

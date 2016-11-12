package ie.gmit.sw;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;


public class StringCompareServiceImpl extends UnicastRemoteObject implements StringService{
	
	private static final long serialVersionUID = 4882785461053507547L;
	private Map<String, StringComparable> algos = new HashMap<String, StringComparable>();
	
	
	////////////////////////////////////
	//quick and dirty, make this nicer//
	////////////////////////////////////
	public StringCompareServiceImpl() throws RemoteException{
		System.out.println("asdfasdfasdfasdf");
		algos.put("damerau-levenshtein distance", new DamerauLevenshtein());
		algos.put("hamming distance", new Hamming());
		algos.put("levenshtein distance", new Levenshtein());
	}
	///////////////////////////////////
	
	
	@Override
	public Resultator compare(String s1, String s2, String alg){
		StringComparable algo = algos.get(alg.toLowerCase());
		Resultator r = null;
		try {
			r = new ResultatorImpl();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		Thread t = new Thread(new AlgorithmWorker(r, algo, s1, s2));
		t.start();
		return r;
	}
}

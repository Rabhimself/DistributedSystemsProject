package ie.gmit.sw.stringcompare;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;


public class StringCompareServiceImpl extends UnicastRemoteObject implements StringService{
	
	private static final long serialVersionUID = 4882785461053507547L;
	private Map<String, Algorithms> algos = new HashMap<String, Algorithms>();
	
	//Loop through all algorithm enums and add it to the map
	//Makes it easy to add or remove any new algorithms without any messy switch statements or hardcoded strings
	public StringCompareServiceImpl() throws RemoteException{
		for(Algorithms a : Algorithms.values()){
			algos.put(a.getName(), a);
		}
	}
	
	
	@Override
	public Resultator compare(String s1, String s2, String alg){
		//Get the algorithm enum from the hashmap and get a new instance of it
		StringComparable algo = algos.get(alg.toLowerCase()).getNewInstance();
		
		//Create the new resultator
		Resultator r = null;
		try {
			r = new ResultatorImpl();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		//Setup a thread and runner to handle the actual computation
		//Just creating anonymous threads on this side of the project
		Thread t = new Thread(new AlgorithmWorker(r, algo, s1, s2));
		t.start();
		
		//return the reference to the resultator
		return r;
	}
}

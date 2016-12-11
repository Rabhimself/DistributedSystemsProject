package ie.gmit.sw.servlet;

import java.rmi.Naming;

import ie.gmit.sw.stringcompare.StringService;

//Hands out references to the StringService, should be set up by servicehandler.init()
//RequestWorkers are the only ones asking for this
public class ServiceResolver {
	
	private static StringService service = null;
	
	public static StringService getService(){
		return service;	
	}
	
	public synchronized static void setService(String host) {
			try {
				service = (StringService) Naming.lookup(host);
			} catch (Exception e) {
				e.printStackTrace();
			}		
	}	
}

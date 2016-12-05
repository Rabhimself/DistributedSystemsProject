package ie.gmit.sw.stringcompare;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Servant{
	
		public static void main(String[] args) throws Exception{
			
			StringService scs = new StringCompareServiceImpl();
			
			LocateRegistry.createRegistry(1099);

			Naming.rebind("StringCompareService", scs);
			System.out.println("String Compare Service listening on port 1099");
		}
	
}

package ie.gmit.sw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ie.gmit.sw.stringcompare.Resultator;

public class ServiceHandler extends HttpServlet {

	
	private static final long serialVersionUID = -1691718013623677859L;
	
	//////////////////////////////////////////////
	//Modify these to adjust the RequestExecutor//
	//////////////////////////////////////////////
	private final static int CORE_POOL_SIZE = 2;
	private final static int MAX_POOL_SIZE = 5;
	private final static long KEEP_ALIVE = 15000;
	//////////////////////////////////////////////
	
	
	private static String remoteHost;
	private volatile static long jobNumber = 0;
	
	//Stores the Resultators, used to check and see if they are done
	private static Map<String, Resultator> outMap = new ConcurrentHashMap<String,Resultator>();
	
	//Managed by the RequestExecutor
	private static BlockingQueue<Runnable> jobQueue = new LinkedBlockingQueue<Runnable>();
	
	//Using a thread pool executor which manages requests automatically using a blockingqueue
	private static RequestExecutor executor = new RequestExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE, TimeUnit.MILLISECONDS, jobQueue);
	

	public void init() throws ServletException {
		ServletContext ctx = getServletContext();
		remoteHost = ctx.getInitParameter("RMI_SERVER");
		//The service resolver gives out references to the rmi server, I moved it to a separate static class(ServiceResolver),
		//so each RequestWorker can get a handle on the rmi server without cluttering up the service handler
		ServiceResolver.setService(remoteHost);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
			
		String algorithm = req.getParameter("cmbAlgorithm");
		String s = req.getParameter("txtS");
		String t = req.getParameter("txtT");
		String taskNumber = req.getParameter("frmTaskNumber");
		String score = null;
		
		if (taskNumber == null){
			//a new job, so set the tasknumber and increment the job number for the next request
			taskNumber = new String("T" + jobNumber);
			jobNumber++;
			//Package everything the runnable will need to handle the job
			Request r = new Request(s,t,taskNumber,algorithm);
			//create a new runnable RequestWorker and pass it off to the threadpoolexecutor
			executor.execute(new RequestWorker(r, outMap));			
		}else{
			//It's a job that's already been submitted, get the appropriate Resultator from the map
			Resultator r = outMap.get(taskNumber);
			
			//If the RMI service is done just print out the result
			if(r.isProcessed()){
				score = r.getResult();
			}
		}
		
		
		out.print("<html><head><title>Distributed Systems Assignment</title>");		
		out.print("</head>");		
		out.print("<body>");

		out.print("<H1>Processing request for Job#: " + taskNumber + "</H1>");
		out.print("<div id=\"r\"></div>");
		
		out.print("<font color=\"#993333\"><b>");
		out.print("RMI Server is located at " + remoteHost);
		out.print("<br>Algorithm: " + algorithm);		
		out.print("<br>String <i>s</i> : " + s);
		out.print("<br>String <i>t</i> : " + t);
		
		//If the Resultator is finished, score will be set at this point
		if(score != null){
			out.print("<br>Distance: " + score);
		}
		
		out.print("<form name=\"frmRequestDetails\">");
		out.print("<input name=\"cmbAlgorithm\" type=\"hidden\" value=\"" + algorithm + "\">");
		out.print("<input name=\"txtS\" type=\"hidden\" value=\"" + s + "\">");
		out.print("<input name=\"txtT\" type=\"hidden\" value=\"" + t + "\">");
		out.print("<input name=\"frmTaskNumber\" type=\"hidden\" value=\"" + taskNumber + "\">");
		out.print("</form>");								
		out.print("</body>");	
		out.print("</html>");	
		
		out.print("<script>");
		//only print the javascript that causes polling if the Resultator wasn't done
		if(score == null)
			out.print("var wait = setTimeout(\"document.frmRequestDetails.submit();\", 10000);");
		
		out.print("</script>");
	}
	
	

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
 	}
	
}
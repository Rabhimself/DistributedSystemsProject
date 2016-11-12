package ie.gmit.sw;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.Naming;
import java.rmi.NotBoundException;
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

public class ServiceHandler extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1691718013623677859L;
	
	private String remoteHost = null;
	private volatile static long jobNumber = 0;
	private static Map<String, Job> jobMap = new ConcurrentHashMap<String,Job>();
	private static BlockingQueue<Runnable> jobQueue = new LinkedBlockingQueue<Runnable>();
	private static StringCompareExecutor executor = new StringCompareExecutor(1, 2, 15000, TimeUnit.MILLISECONDS, jobQueue);
	

	public void init() throws ServletException {
		ServletContext ctx = getServletContext();
		remoteHost = ctx.getInitParameter("RMI_SERVER");
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringCompareService scs = null;
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
			
				
		try {
			scs = (StringCompareService) Naming.lookup("rmi://"+remoteHost+":1099/StringCompareService");
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String algorithm = req.getParameter("cmbAlgorithm");
		String s = req.getParameter("txtS");
		String t = req.getParameter("txtT");
		String taskNumber = req.getParameter("frmTaskNumber");
		
		int score = -1;
		if (taskNumber == null){
			taskNumber = new String("T" + jobNumber);
			jobNumber++;
			Job j = new Job(s, t, taskNumber, algorithm);
			jobMap.put(taskNumber, j);
			executor.execute(new StringCompareWorker(j, scs));
		}else{
			Job j = jobMap.get(taskNumber);
			if(j.isComplete())
			{
				score = j.getScore();
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
		if(score != -1){
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
		if(score == -1)
			out.print("var wait = setTimeout(\"document.frmRequestDetails.submit();\", 10000);");
		out.print("</script>");
	}
	
	

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
 	}
	
}
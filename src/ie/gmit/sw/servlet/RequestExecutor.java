package ie.gmit.sw.servlet;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//only extended for overriding the before and after execute methods
//for testing purposes you can modify the pool sizes in the service handler class, or just hardcode your values into the super() call
public class RequestExecutor extends ThreadPoolExecutor {
 
    public RequestExecutor(int corePoolSize, int maximumPoolSize,
            long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }   
    
    //These are only for printing thread status in console////////
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        System.out.println(t+ " is about to run " +r +", there are "+this.getQueue().size()+" tasks left in the queue!");
        
    }
 
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        if (t != null) {
            System.out.println("Perform exception handler logic");
        }
        System.out.println(r +" is done! There are "+this.getQueue().size()+" tasks left in the queue!");
    }
    ////////////////////////////////////////////////////////////////
}
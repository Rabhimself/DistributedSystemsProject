package ie.gmit.sw;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class StringCompareExecutor extends ThreadPoolExecutor {
 
    public StringCompareExecutor(int corePoolSize, int maximumPoolSize,
            long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }   
    
    //Might use these for printing thread status in console////////
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
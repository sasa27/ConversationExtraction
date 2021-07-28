package parralelisation;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import java.util.concurrent.FutureTask;
import java.util.concurrent.Future;
import metrique.*;
import objetsconversations.*;
import metrique.*;
import split.*;

public class ThreadExecutorFinding {
	public ThreadPoolExecutor threadpool;
    public BlockingQueue<Runnable> queue;
	public HashSet<HashSet<Set<String>>> result;
    public ThreadExecutorFinding(){ 
        queue=new LinkedBlockingQueue<Runnable>();
        threadpool = new SimpleThreadPoolFinding(queue);
        result=new HashSet<HashSet<Set<String>>>();
    }
    public ThreadPoolExecutor getThreadpool(){
        return this.threadpool;
    }
    public BlockingQueue getqueue(){
        return this.queue;
    }
    
    public void shut() {
    	this.threadpool.shutdown();
    }
    
    public void SubmitFindingTask(TaskFinder task) {
        this.threadpool.submit(task);
    }
}
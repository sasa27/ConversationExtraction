package parralelisation;

import java.util.Comparator;



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

public class ThreadExecutorSimpleBlockingQueue {
	public ThreadPoolExecutor threadpool;
    public int numberThread;
    public BlockingQueue<Runnable> queue;
    public ThreadExecutorSimpleBlockingQueue(){
    	numberThread=0;
        threadpool = new SimpleThreadPool();
        queue=new LinkedBlockingQueue<Runnable>();
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
    
    public void SubmitTask(Task task) {
        this.threadpool.submit(task);
    }
}

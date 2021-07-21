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
    public BlockingQueue<Runnable> queue;
    public ThreadExecutorSimpleBlockingQueue(){ 
        queue=new LinkedBlockingQueue<Runnable>();
        threadpool = new SimpleThreadPool(queue);
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
    public void SubmitMinimizingTask(TaskMinimizing task) {
        this.threadpool.submit(task);
    }
}

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

public class ThreadExecutor {
	public ThreadPoolExecutor threadpool;
    public static Comparator<MyFutureTask> heuristiquesorter;
    public int numberThread;
    public PriorityBlockingQueue<Runnable> queue;
    public ThreadExecutor(){
    	numberThread=0;
        queue=new PriorityBlockingQueue<Runnable>();
        threadpool = new CustomThreadPoolExecutor(queue);
    }
    public ThreadPoolExecutor getThreadpool(){
        return this.threadpool;
    }
    public PriorityBlockingQueue getqueue(){
        return this.queue;
    }
    
    public void shut() {
    	this.threadpool.shutdown();
    }
    
    public void SubmitTask(Task task) {
    	/*
    	this.numberThread+=1;
    	if (this.numberThread % 10000 == 0) {
    		System.out.println(this.numberThread);
    		System.out.println(this.threadpool);
    	}*/
        this.threadpool.submit(task);
    }
}

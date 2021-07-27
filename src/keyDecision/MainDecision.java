package keyDecision;
import java.util.ArrayList;

import java.util.Set;
import java.util.concurrent.BlockingQueue;

import parralelisation.*;
import objetsconversations.*;

public class MainDecision {
	public void main(){
		GroupOfAllFiles allFiles =new GroupOfAllFiles();
		testFirstFile=;
		testSecondFile=;
		for 
		GroupOfKeysInOneFile firstFile= new GroupOfKeysInOneFile();
		ThreadExecutorSimpleBlockingQueue pool = new ThreadExecutorSimpleBlockingQueue();
		
		
		GroupOfAllFiles toUse = new GroupOfAllFiles();
		GroupOfAllFiles createdGroup = new GroupOfAllFiles();
		for (GroupOfKeysInOneFile file : toUse.groupOfAllFiles) {
			pool.SubmitMinimizingTask(new TaskMinimizing(file, createdGroup));
		}
		while (!pool.threadpool.isTerminated()) {
        	try {
        	    Thread.sleep(1000);
        	} catch (InterruptedException ie) {
        		System.err.println("erreur sleep");
        	}
        }
		ThreadExecutorSimpleBlockingQueue newPool = new ThreadExecutorSimpleBlockingQueue();
		GroupOfKeysInOneFile firstS=createdGroup.groupOfAllFiles.get(0);
		for(KeysFound firstKeys: firstS.groupOfKeysFound) {
			newPool.SubmitFindingTask(new TaskFinder(createdGroup, firstKeys, newPool ,0));
		}
		while (!newPool.threadpool.isTerminated()) {
        	try {
        	    Thread.sleep(1000);
        	} catch (InterruptedException ie) {
        		System.err.println("erreur sleep");
        	}
        }
	}
}

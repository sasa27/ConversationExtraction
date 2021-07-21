package keyDecision;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

import parralelisation.*;
import objetsconversations.*;

public class MainDecision {
	public void main(){
		ThreadExecutorSimpleBlockingQueue pool = new ThreadExecutorSimpleBlockingQueue();
		GroupOfKeys CreatedGroup = new GroupOfKeys();
		for(KeysFound S : CreatedGroup.groupOfKeysFound){
			pool.SubmitMinimizingTask(TaskMinimizing(S, CreatedGroup, CreatedGroup.groupOfKeysFound.indexOf(S)));
		}
		while (!pool.threadpool.isTerminated()) {
        	try {
        	    Thread.sleep(1000);
        	} catch (InterruptedException ie) {
        		System.err.println("erreur sleep");
        	}
        }
		ThreadExecutorSimpleBlockingQueue newPool = new ThreadExecutorSimpleBlockingQueue();
		firstS=CreatedGroup.getS(0);
		for( Set<String> firstKeys: firstS.Keys) {
			newPool.SubmitFindingTask(S);
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

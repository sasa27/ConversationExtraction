package keyDecision;
import java.io.File;
import java.util.ArrayList;

import java.util.Set;
import java.util.concurrent.BlockingQueue;

import main.SavingResults;
import parralelisation.*;
import objetsconversations.*;

public class MainDecision {
	public void main(){
		GroupOfAllFiles allFiles =new GroupOfAllFiles();
		GroupOfKeysInOneFile firstFile= new GroupOfKeysInOneFile();
		KeysFound aFile = new KeysFound();
		File folder = new File("../../RESULTS/Log2/output0/convSetnumber1");
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
		    if (file.getName().contains("ents.txt")) {
		    	aFile.unauthorizedKeys = SavingResults.loadUnauthorizedArrayListInFile(file);
		    }
		    else {
		    	aFile.keys = SavingResults.loadArrayListInFile(file);
		    }
		}
		firstFile.groupOfKeysFound.add(aFile);
		allFiles.groupOfAllFiles.add(firstFile);
		System.out.println(allFiles);
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

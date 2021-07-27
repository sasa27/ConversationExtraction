package keyDecision;
import java.io.File;
import java.util.ArrayList;

import java.util.Set;
import java.util.concurrent.BlockingQueue;

import main.SavingResults;
import parralelisation.*;
import objetsconversations.*;

public class MainDecision {
	public static void main(){
		GroupOfAllFiles allFiles =new GroupOfAllFiles();
		GroupOfKeysInOneFile firstFile= new GroupOfKeysInOneFile();
		KeysFound aFile = new KeysFound();
		File folder = new File("RESULTS/log2/output0/convSetnumber1");
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
		    if (file.getName().contains("ents.txt")) {
		    	aFile.unauthorizedKeys = SavingResults.loadUnauthorizedArrayListInFile(file);
		    }
		    else {
		    	aFile.keys = SavingResults.loadArrayListInFile(file);
		    }
		}
		
		GroupOfKeysInOneFile SecondFile= new GroupOfKeysInOneFile();
		KeysFound aSecondFile = new KeysFound();
		File folder2 = new File("RESULTS/log21/output0/convSetnumber1");
		File[] listOfFiles2 = folder2.listFiles();
		for (File file : listOfFiles2) {
		    if (file.getName().contains("ents.txt")) {
		    	aSecondFile.unauthorizedKeys = SavingResults.loadUnauthorizedArrayListInFile(file);
		    }
		    else {
		    	aSecondFile.keys = SavingResults.loadArrayListInFile(file);
		    }
		}
		firstFile.groupOfKeysFound.add(aSecondFile);
		allFiles.groupOfAllFiles.add(SecondFile);
		ThreadExecutorSimpleBlockingQueue pool = new ThreadExecutorSimpleBlockingQueue();
		

		GroupOfAllFiles createdGroup = new GroupOfAllFiles();
		System.out.println("debug la");
		for (GroupOfKeysInOneFile file : allFiles.groupOfAllFiles) {
			pool.SubmitMinimizingTask(new TaskMinimizing(file, createdGroup));
		}
		
		while (!(pool.getqueue().size()==0)) {
        	try {
        	    Thread.sleep(1000);
        	} catch (InterruptedException ie) {
        		System.err.println("erreur sleep");
        	}
        }
		System.out.println(createdGroup);
		System.out.println("ici");
		ThreadExecutorSimpleBlockingQueue newPool = new ThreadExecutorSimpleBlockingQueue();
		System.out.println(createdGroup.groupOfAllFiles.size());
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

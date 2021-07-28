package keyDecision;
import java.io.File;
import java.util.ArrayList;

import java.util.Set;
import java.util.concurrent.BlockingQueue;

import main.SavingResults;
import parralelisation.*;
import objetsconversations.*;

public class MainDecision {
	public GroupOfAllFiles allFiles;
	public GroupOfAllFiles createdGroup;
	
	public static void main(){
		GroupOfAllFiles allFiles =new GroupOfAllFiles();
		
		KeysFound aFile = new KeysFound();
		final File folder = new File("RESULTS");
		ArrayList<File> listOfFiles = listFilesForFolderFromResult(folder);
		listFilesForFolder(folder,allFiles);

		ThreadExecutorSimpleBlockingQueue pool = new ThreadExecutorSimpleBlockingQueue();
		
		
		GroupOfAllFilesMinimized group = new GroupOfAllFilesMinimized();
		for (GroupOfKeysInOneFile file : allFiles.groupOfAllFiles) {
			pool.SubmitMinimizingTask(new TaskMinimizing(file, group));
		}
		try {
    	    Thread.sleep(5000);
    	} catch (InterruptedException ie) {
    		System.err.println("erreur sleep");
    	}
		while (!(pool.getqueue().size()==0)) {
        	try {
        	    Thread.sleep(1000);
        	} catch (InterruptedException ie) {
        		System.err.println("erreur sleep");
        	}
        }
		pool.threadpool.shutdown();
		ThreadExecutorFinding newPool = new ThreadExecutorFinding();
		GroupOfKeysInOneFile firstS=group.groupOfAllFiles.get(0);
		for(KeysFound firstKeys: firstS.groupOfKeysFound) {
			
			newPool.SubmitFindingTask(new TaskFinder(group, firstKeys, newPool ,0));
		}
		while (!newPool.threadpool.isTerminated()) {
        	try {
        	    Thread.sleep(1000);
        	} catch (InterruptedException ie) {
        		System.err.println("erreur sleep");
        	}
        }
	}
	
	
	public static ArrayList<File> listFilesForFolderFromResult(final File folder) {
		ArrayList<File> listOfFiles= new ArrayList<File>();
		for (final File fileEntry : folder.listFiles()) {
			listOfFiles.add(fileEntry);
		}
		return listOfFiles;
	}
	
	public static void listFilesForFolder(final File folder, GroupOfAllFiles AllFiles) {
	    for (final File fileEntry : folder.listFiles()) {
	        if ((fileEntry.isDirectory()) &&(!folder.getName().contains("result"))) {
	        	GroupOfKeysInOneFile SecondFile= new GroupOfKeysInOneFile();
	        	for(File outp : fileEntry.listFiles()) {
	        		File[] listOfFiles2 = outp.listFiles();
	        		KeysFound aSecondFile = new KeysFound();
	        		for (File file : listOfFiles2) {
	        		    if (file.getName().contains("ents.txt")) {
	        		    	aSecondFile.unauthorizedKeys = SavingResults.loadUnauthorizedArrayListInFile(file);
	        		    }
	        		    else {
	        		    	aSecondFile.keys = SavingResults.loadArrayListInFile(file);
	        		    }
	        		}
	        		SecondFile.groupOfKeysFound.add(aSecondFile);
	        	}
	        
	        }
	    }
	}
	
	public void createKeysFound(File file, KeysFound aFile){
	    	if (file.getName().contains("ents.txt")) {
	    		aFile.unauthorizedKeys = SavingResults.loadUnauthorizedArrayListInFile(file);
	    	}
	    	else {
	    		aFile.keys = SavingResults.loadArrayListInFile(file);
	    	}
		}
}

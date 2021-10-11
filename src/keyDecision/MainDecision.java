package keyDecision;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

import lastExtraction.ExtractionWithKeys;
import main.ResultsWriter;
import main.SavingResults;
import parralelisation.*;
import split.Trace;
import objetsconversations.*;

public class MainDecision {
	public GroupOfAllFiles allFiles;
	public GroupOfAllFiles createdGroup;
	
	
	/**
	* main for the second part of the programm which is finding the global keys common to all files
	* @return      void
	*/
	
	public static void main(Trace trace){
		GroupOfAllFiles allFiles =new GroupOfAllFiles();
		final File folder = new File("RESULTS");
		ArrayList<File> listOfFiles = listFilesForFolderFromResult(folder);
		for(File aFile : listOfFiles) {
			listFilesForFolder(aFile,allFiles);
		}

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
			System.out.println("par ici");
			newPool.SubmitFindingTask(new TaskFinder(group, firstKeys, newPool ,0));
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
		HashSet<HashSet<Set<String>>> allKeysPossible =new HashSet<HashSet<Set<String>>>(newPool.result);
		ResultsWriter.writeAllPossibleKeys(newPool.result);
		newPool.threadpool.shutdown();
		System.out.println(allKeysPossible.size());
		for (HashSet<Set<String>> separateKeys : allKeysPossible) {
			System.out.println("Apres shut");
			ExtractionWithKeys.extractionWithAllPossibleKeys(trace, separateKeys);
		}
	}
	
	/**
	* List all files inside a folder
	* @param  folder a folder
	* @return listOfFiles all files into a folder
	*/
	public static ArrayList<File> listFilesForFolderFromResult(final File folder) {
		ArrayList<File> listOfFiles= new ArrayList<File>();
		for (final File fileEntry : folder.listFiles()) {
			listOfFiles.add(fileEntry);
		}
		return listOfFiles;
	}
	
	/**
	* List all files inside a folder from results of the precedent programm and add it to the total off all keys from all folders
	* @param  folder a folder
	* @param allFiles the group of every keys from every files
	* @return void
	*/
	
	
	public static void listFilesForFolder(final File folder, GroupOfAllFiles allFiles) {
		GroupOfKeysInOneFile SecondFile= new GroupOfKeysInOneFile();
	    for (final File fileEntry : folder.listFiles()) {
	    	
	        if ((fileEntry.isDirectory()) &&(!fileEntry.getName().contains("result"))) {
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
	        		if ((!aSecondFile.keys.isEmpty())&& (!aSecondFile.unauthorizedKeys.isEmpty())) {
	        			SecondFile.groupOfKeysFound.add(new KeysFound(aSecondFile));
	        		}
	        	}
	        
	        }
	       
	    }
	    allFiles.groupOfAllFiles.add(new GroupOfKeysInOneFile(SecondFile));
	}
	
	/**
	* save all keys and unauthorizedKeys from a file in a KeysFound
	* @param  file a file from which we will get out all keys saved
	* @param  aFile KeysFound empty at the beginning
	* @return listOfFiles all files into a folder
	*/
	
	public void createKeysFound(File file, KeysFound aFile){
	    	if (file.getName().contains("ents.txt")) {
	    		aFile.unauthorizedKeys = SavingResults.loadUnauthorizedArrayListInFile(file);
	    	}
	    	else {
	    		aFile.keys = SavingResults.loadArrayListInFile(file);
	    	}
		}
}

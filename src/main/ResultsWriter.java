package main;
import objetsconversations.*;


import split.*;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.Collections;
import java.util.HashSet;

public class ResultsWriter {
	
	
	
	public static synchronized void write(ConversationSet ensemble, Trace trace, Save sauv) {
		
		
		
		
		trace.compteur+=1;
		File directoryFile=new File(sauv.file+"/resultat"+sauv.number);
		File directoryOutput=new File(sauv.file+"/output"+sauv.number);
		if ((!directoryFile.exists()) || (!directoryFile.isDirectory())){
			directoryFile.mkdir();
		}
		if ((!directoryOutput.exists()) || (!directoryOutput.isDirectory())){
			directoryOutput.mkdir();
		}
			File dir = new File(sauv.file+"/resultat"+sauv.number+"/trace"+trace.compteur+".txt");
				Set<String> totalKeys = new HashSet<String>();
				
				for (Conversation conv : ensemble.getConversationSet()) {
					for (ArrayList<String> key : conv.getChoosedKeys()) {
						totalKeys.add(key.toString());
					}
				}
				
			    String writingInFile = "";
			    writingInFile= writingInFile  +"Used Keys in the ConversationSet : "+  "\n";
			    for (String keysToWrite : totalKeys) {
			    	 writingInFile= writingInFile + keysToWrite+"\n";
			    }
			    writingInFile= writingInFile + "\n \n \n Conversations found with this set of keys : \n \n";
			    int compteurOfConversations=0;
			    for (Conversation conv : ensemble.getConversationSet()) {
			    	writingInFile= writingInFile  +"conversation number "+ compteurOfConversations + "\n";
			    	for (Event evenement : conv.getConv()) {
			    		writingInFile= writingInFile  +evenement.getligne()+ "\n";
			    	}
			    	compteurOfConversations++;
			    }
			    
			    writingInFile = writingInFile + "\n \n \n \n";
			    writingInFile = writingInFile + ensemble.getHeuristique(ensemble) + "\n";
			    try {
			    FileWriter fw = new FileWriter(dir.getAbsoluteFile());
			    BufferedWriter bw = new BufferedWriter(fw);
			    bw.write(writingInFile);
			    bw.close();
			    if ((!directoryFile.exists()) || (!directoryFile.isDirectory())){
					directoryFile.mkdir();
				}
			    File directoryFileOutput=new File(sauv.file+"/output"+sauv.number+"/convSetnumber"+trace.compteur);
				if ((!directoryFileOutput.exists()) || (!directoryFileOutput.isDirectory())){
					directoryFileOutput.mkdir();
				}
				File dirArray = new File(sauv.file+"/output"+sauv.number+"/convSetnumber"+trace.compteur+"/cles.txt");
				File dirOutputAssignments = new File(sauv.file+"/output"+sauv.number+"/convSetnumber"+trace.compteur+"/assignments.txt");
				//System.out.println(ensemble.getAllKeys());
			    SavingResults.writeArrayListInFile(ensemble.getAllKeys(), dirArray);
			    ArrayList<Set<String>> keys = SavingResults.loadArrayListInFile(dirArray);
			    SavingResults.writeUnauthorizedArrayListInFile(ensemble,keys,dirOutputAssignments);
			  //  threadpool.threadpool.shutdownNow();
			    }
			    catch(Exception e) {
			    	System.err.println(e);
			    }
		
	    }
public static synchronized void writeAllPossibleKeys(HashSet<HashSet<Set<String>>> allPossibleKeys) {
	File dir = new File("RESULTS/allPossibleKeys.txt");
	 String writingInFile = "";
	 writingInFile= writingInFile  +"All Possible Keys From The File : \n \n";
	 for (HashSet<Set<String>> firstLayer : allPossibleKeys) {
		 writingInFile= writingInFile  + firstLayer.toString() + " \n";
	 }

	 try {
		    FileWriter fw = new FileWriter(dir.getAbsoluteFile());
		    BufferedWriter bw = new BufferedWriter(fw);
		    bw.write(writingInFile);
		    bw.close();
	 }
	 catch(Exception e) {
	    	System.err.println(e);
	    }
	}
		
		
}

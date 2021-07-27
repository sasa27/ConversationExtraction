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
public static synchronized void writeEasyToUnderstand(ConversationSet ensemble, Trace trace, Save sauv) {
		
		
		
		
		trace.compteur+=1;
		File dossier=new File(sauv.file+"/resultat"+sauv.number);
		if ((!dossier.exists()) || (!dossier.isDirectory())){
			dossier.mkdir();
		}
			File dir = new File(sauv.file+"/resultat"+sauv.number+"/trace"+trace.compteur+".txt");
				
				ArrayList<ArrayList<String>> usedKeys= new ArrayList<ArrayList<String>>();
			    String writingInFile = "";
			    for (Conversation conv : ensemble.getConversationSet()) {
			    	for (Event evenement : conv.getConv()) {
			    		writingInFile= writingInFile  +evenement.getligne()+ "\n";
			    		for (ArrayList<String> key :conv.getChoosedKeys()) {
				    		if (!usedKeys.contains(key)) {
				    			usedKeys.add(key);
				    		}
			    		}
			    	}
			    }
			    writingInFile = writingInFile + ensemble.getHeuristique(ensemble) + "\n";
			    writingInFile = writingInFile + ensemble.getHeuristique(ensemble) + "\n";
			    try {
			    FileWriter fw = new FileWriter(dir.getAbsoluteFile());
			    BufferedWriter bw = new BufferedWriter(fw);
			    bw.write(writingInFile);
			    bw.close();
			  //  threadpool.threadpool.shutdownNow();
			    }
			    catch(Exception e) {
			    	System.err.println(e);
			    }
		
	    }
	
	
	
	
	
	//méthode pour écrire plusieurs dossier quand on utilise plusieurs fichiers de tests a la suite
	public static synchronized void writeMoreThanOne(ConversationSet ensemble, Trace trace, Save sauv) {
		
		
		
		
		
		
	//if (ensemble.ConvSet.size()==1) {
		trace.compteur+=1;
		File directory=new File(sauv.file+"/resultat"+sauv.number);
		if ((!directory.exists()) || (!directory.isDirectory())){
			directory.mkdir();
		}
		File dir = new File(sauv.file+"/resultat"+sauv.number+"/trace"+trace.compteur+".txt");
	    
	    String writingInFile = "";
	    ArrayList<String> keysAlone = new ArrayList<String>();
	    Set<String> SetParam= new LinkedHashSet<>();
	    ArrayList<ArrayList<String>> arrayOfArrayOfKeys = new ArrayList<ArrayList<String>>();
	    for (Conversation conv : ensemble.getConversationSet()) {
	    	ArrayList<ArrayList<String>> arajout=conv.getChoosedKeys();
	    	for (ArrayList<String> key : arajout) {
	    		String keyInString = key.toString();
	    		keyInString=keyInString.replace("[", "").replace("]","").replace(" ", "");
	    		String[] keySplited=keyInString.split("=");
	    		if (keySplited.length==2) {
	    			String keyAlone=keySplited[0];
	    			keysAlone.add(keyAlone);
		    		SetParam.add(keyAlone);
	    		}
	    		else {
	    			ArrayList<String> moreThanOneKey = new ArrayList<String>();
	    			for(int i=0;i<keySplited.length;i+=2) {
	    				
	    				moreThanOneKey.add(keySplited[i]);
	    				String resultat4=keySplited[i];
	    			}
	    			arrayOfArrayOfKeys.add(moreThanOneKey);
	    		}
	    	}
	    }
	    sauv.addToSave(SetParam,keysAlone, ensemble, arrayOfArrayOfKeys);
	    for (Conversation conv : ensemble.getConversationSet()) {
	    	for (Event evenement : conv.getConv()) {
	    		writingInFile= writingInFile  +evenement.getligne()+ "\n";
	    	}
	    	writingInFile = writingInFile + "\n \n";
	    	writingInFile = writingInFile + conv.getChoosedKeys() + "\n" + "req : " + conv.getReq() + "         rep : " + conv.getRep() ;
	    	writingInFile = writingInFile + "\n \n \n \n";
	    }
	    
	    writingInFile = writingInFile + "\n \n \n \n";
	    writingInFile = writingInFile + ensemble.getHeuristique(ensemble) + "\n";
	    try {
	    FileWriter fw = new FileWriter(dir.getAbsoluteFile());
	    BufferedWriter bw = new BufferedWriter(fw);
	    bw.write(writingInFile);
	    bw.close();
	  //  threadpool.threadpool.shutdownNow();
	    }
	    catch(Exception e) {
	    	System.err.println(e);
	    }
	//}
    }
}

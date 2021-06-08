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

public class ResultsWriter {
	
	public static synchronized void write(ConversationSet ensemble, Trace trace, Save sauv) {
		
		
		
		
		
			File dir = new File(sauv.file+"/resultat"+sauv.number+"/trace"+trace.compteur+".txt");
				trace.compteur+=1;
				File dossier=new File(sauv.file+"/resultat"+sauv.number);
				if ((!dossier.exists()) || (!dossier.isDirectory())){
					dossier.mkdir();
				}
				
			    String writingInFile = "";
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
		
	    }
	
	
	
	
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

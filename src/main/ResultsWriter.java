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
	
	public static synchronized void write(ConversationSet ensemble, Trace trace, sauvegarde sauv) {
		
		
		
		
		
			File dir = new File(sauv.file+"/resultat"+sauv.nombre+"/trace"+trace.compteur+".txt");
				trace.compteur+=1;
				File dossier=new File(sauv.file+"/resultat"+sauv.nombre);
				if ((!dossier.exists()) || (!dossier.isDirectory())){
					dossier.mkdir();
				}
				
			    String renvoi = "";
			    for (Conversation conv : ensemble.getConversationSet()) {
			    	for (Event evenement : conv.getConv()) {
			    		renvoi= renvoi  +evenement.getligne()+ "\n";
			    	}
			    	renvoi = renvoi + "\n \n";
			    	renvoi = renvoi + conv.getClechoisi() + "\n" + "req : " + conv.getReq() + "         rep : " + conv.getRep() ;
			    	renvoi = renvoi + "\n \n \n \n";
			    }
			    
			    renvoi = renvoi + "\n \n \n \n";
			    renvoi = renvoi + ensemble.getHeuristique(ensemble) + "\n";
			    try {
			    FileWriter fw = new FileWriter(dir.getAbsoluteFile());
			    BufferedWriter bw = new BufferedWriter(fw);
			    bw.write(renvoi);
			    bw.close();
			  //  threadpool.threadpool.shutdownNow();
			    }
			    catch(Exception e) {
			    	System.err.println(e);
			    }
		
	    }
	
	
	
	
	public static synchronized void writeMoreThanOne(ConversationSet ensemble, Trace trace, sauvegarde sauv) {
		
		
		
		
		
		
	//if (ensemble.ConvSet.size()==1) {
		trace.compteur+=1;
		File dossier=new File(sauv.file+"/resultat"+sauv.nombre);
		if ((!dossier.exists()) || (!dossier.isDirectory())){
			dossier.mkdir();
		}
		File dir = new File(sauv.file+"/resultat"+sauv.nombre+"/trace"+trace.compteur+".txt");
	    
	    String renvoi = "";
	    ArrayList<String> etude = new ArrayList<String>();
	    Set<String> SetParam= new LinkedHashSet<>();
	    ArrayList<ArrayList<String>> plusieurscles = new ArrayList<ArrayList<String>>();
	    for (Conversation conv : ensemble.getConversationSet()) {
	    	ArrayList<ArrayList<String>> arajout=conv.getClechoisi();
	    	for (ArrayList<String> raj : arajout) {
	    		String resultat = raj.toString();
	    		resultat=resultat.replace("[", "").replace("]","").replace(" ", "");
	    		String[] resultat2=resultat.split("=");
	    		if (resultat2.length==2) {
	    			String resultat4=resultat2[0];
	    			etude.add(resultat4);
		    		SetParam.add(resultat4);
	    		}
	    		else {
	    			ArrayList<String> rajoutplus = new ArrayList<String>();
	    			for(int i=0;i<resultat2.length;i+=2) {
	    				
	    				rajoutplus.add(resultat2[i]);
	    				String resultat4=resultat2[i];
	    			}
	    			plusieurscles.add(rajoutplus);
	    		}
	    	}
	    }
	    sauv.addtoSauv(SetParam,etude, ensemble, plusieurscles);
	    for (Conversation conv : ensemble.getConversationSet()) {
	    	for (Event evenement : conv.getConv()) {
	    		renvoi= renvoi  +evenement.getligne()+ "\n";
	    	}
	    	renvoi = renvoi + "\n \n";
	    	renvoi = renvoi + conv.getClechoisi() + "\n" + "req : " + conv.getReq() + "         rep : " + conv.getRep() ;
	    	renvoi = renvoi + "\n \n \n \n";
	    }
	    
	    renvoi = renvoi + "\n \n \n \n";
	    renvoi = renvoi + ensemble.getHeuristique(ensemble) + "\n";
	    try {
	    FileWriter fw = new FileWriter(dir.getAbsoluteFile());
	    BufferedWriter bw = new BufferedWriter(fw);
	    bw.write(renvoi);
	    bw.close();
	  //  threadpool.threadpool.shutdownNow();
	    }
	    catch(Exception e) {
	    	System.err.println(e);
	    }
	//}
    }
}

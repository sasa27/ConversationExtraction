package algo;
import main.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import java.util.ArrayList;

import java.util.List;


//import org.apache.log4j.*;

import metrique.*;
import objetsconversations.*;
import conditions.*;
import split.Trace;
import parralelisation.*;
import split.Regex;

import java.io.BufferedWriter;

public class FindConversation {

	//ensemble de conversation
	private static ArrayList<Conversation> ensembleconv;

	//ce que l'on va écrire dans le fichier
	public static String output;
	public static boolean TraceValide;
	public static boolean nouveau;
	public static void Find(ConversationSet ensemble, int ligne , Boolean fini, Trace trace, ThreadExecutor threadpool, Regex regex,sauvegarde sauv){
		System.out.println(ligne);
		
		if(ligne!=trace.getSize()) {
			boolean bon=false;
			Event eventmoment=trace.getEvent(ligne);

			ensembleconv = ensemble.getConversationSet();
			
			if (ligne>=1) {
			//createNewConversationSetIfPossible
				ArrayList<ConversationSet> nouvelleliste = new ArrayList<ConversationSet>();
				for (Conversation conver : ensembleconv) {
					ArrayList<ArrayList<String>> Intersection= ParcoursConversationsSet.intersection(eventmoment, conver);
					
					if (Invariant.invariantintersect(Intersection)) {
						
						for (ArrayList<String> inter : Intersection) {
							boolean verificationToutesConversations=true;
							Conversation nouvelleconv = new Conversation(conver,inter, eventmoment);
							ConversationSet Convers = new ConversationSet(ensemble,conver,nouvelleconv, inter, eventmoment);
							for (Conversation conversationVerif : Convers.ConvSet) {
								if ((!Invariant.Invariant2(Convers, conversationVerif)) || (!Invariant.Invariant3(Convers,conversationVerif))) {
										verificationToutesConversations = false;
								}
							}
							
							if(verificationToutesConversations) {
								
								checkTresholdAndSubmitNewCreatedTask(new ConversationSet(ensemble,conver,new Conversation(conver,inter, eventmoment), inter, eventmoment), ligne , fini, trace, threadpool,regex, sauv);
								bon=true;
							}
						}
					}
				}
			}
			
			//tryNewConversationWithTheCurrentEvent
				Conversation nouvelleconv2 = new Conversation(eventmoment);
				ensemble.ConvSet.add(nouvelleconv2);
				boolean verificationToutesConversations= true;
				for (Conversation conversationVerif : ensemble.ConvSet) {
					if ((!Invariant.Invariant2(ensemble, conversationVerif)) || (!Invariant.Invariant3(ensemble,conversationVerif))) {
							verificationToutesConversations = false;
						
					}
				}
				if(verificationToutesConversations) {
					ConversationSet ensemble2=new ConversationSet(ensemble);
					checkTresholdAndSubmitNewCreatedTask(ensemble2, ligne , fini, trace, threadpool,regex, sauv);
					bon=true;
				}
				
		ensemble=null;
		
		}
		
		else {
			ResultsWriter.write(ensemble, trace, sauv);
			sauv.nbconv+=1;
			
		}
		return ;
	
	}
	

	
	
	
	
	
	
	
	public static void checkTresholdAndSubmitNewCreatedTask(ConversationSet ensemble, int ligne , Boolean fini, Trace trace, ThreadExecutor threadpool, Regex regex,sauvegarde sauv) {
		if (TraceCondition.treshold(ensemble, ligne)) {
			try {
				Task tache = new Task("i",ensemble,ligne+1,false,trace, threadpool , regex, sauv);
				threadpool.SubmitTask(tache);
			}
			catch(Exception e){
				System.err.println(e);
			}
		}
	}
}


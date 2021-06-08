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


public class FindConversation {

	private static ArrayList<Conversation> ensembleconv;
	
	public static void Find(ConversationSet ensemble, int line , Boolean end, Trace trace, ThreadExecutor threadpool, Regex regex,Save sauv){
		System.out.println(line);
		
		if(line!=trace.getSize()) {
			Event eventmoment=trace.getEvent(line);

			ensembleconv = ensemble.getConversationSet();
			
			if (line>=1) {
			//createNewConversationSetIfPossible
				for (Conversation conver : ensembleconv) {
					ArrayList<ArrayList<String>> Intersection= ParcoursConversationsSet.intersection(eventmoment, conver);
					
					if (Invariant.invariantintersect(Intersection)) {
						
						for (ArrayList<String> inter : Intersection) {
							boolean verificationToutesConversations=true;
							Conversation newConv = new Conversation(conver,inter, eventmoment);
							ConversationSet newConvSet = new ConversationSet(ensemble,conver,newConv, inter, eventmoment);
							for (Conversation conversationVerif : newConvSet.ConvSet) {
								if ((!Invariant.Invariant2(newConvSet, conversationVerif)) || (!Invariant.Invariant3(newConvSet,conversationVerif))) {
										verificationToutesConversations = false;
								}
							}
							
							if(verificationToutesConversations) {
								
								checkTresholdAndSubmitNewCreatedTask(new ConversationSet(ensemble,conver,new Conversation(conver,inter, eventmoment), inter, eventmoment), line , end, trace, threadpool,regex, sauv);
							}
						}
					}
				}
			}
			
			//tryNewConversationWithTheCurrentEvent
				Conversation nouvelleconv2 = new Conversation(eventmoment);
				ensemble.ConvSet.add(nouvelleconv2);
				boolean invariantVerificationForAllConversations= true;
				for (Conversation conversationVerif : ensemble.ConvSet) {
					if ((!Invariant.Invariant2(ensemble, conversationVerif)) || (!Invariant.Invariant3(ensemble,conversationVerif))) {
							invariantVerificationForAllConversations = false;
						
					}
				}
				if(invariantVerificationForAllConversations) {
					ConversationSet ensemble2=new ConversationSet(ensemble);
					checkTresholdAndSubmitNewCreatedTask(ensemble2, line , end, trace, threadpool,regex, sauv);
				}
				
		ensemble=null;
		
		}
		
		else {
			
			ResultsWriter.write(ensemble, trace, sauv);
			sauv.nbconv+=1;
			if (end) {
				threadpool.threadpool.shutdownNow();
			}
		}
		return ;
	
	}
	

	
	
	
	
	
	
	
	public static void checkTresholdAndSubmitNewCreatedTask(ConversationSet ensemble, int ligne , Boolean end, Trace trace, ThreadExecutor threadpool, Regex regex,Save sauv) {
		if (TraceCondition.treshold(ensemble, ligne)) {
			try {
				Task tache = new Task(ensemble,ligne+1,end,trace, threadpool , regex, sauv);
				threadpool.SubmitTask(tache);
			}
			catch(Exception e){
				System.err.println(e);
			}
		}
	}
}


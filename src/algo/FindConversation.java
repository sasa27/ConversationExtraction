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

	private static ArrayList<Conversation> ensembleOfConversations;
	
	public static void Find(ConversationSet ensemble, int line , Boolean end, Trace trace, ThreadExecutor threadpool, Regex regex,Save sauv){
		System.out.println(line);
		
		if(line!=trace.getSize()) {
			Event currentEvent=trace.getEvent(line);

			ensembleOfConversations = ensemble.getConversationSet();
			
			if (line>=1) {
				createNewConversationSetIfPossible(ensemble, line , end, trace, threadpool, regex, sauv, currentEvent);
			}
			tryNewConversationWithTheCurrentEvent(ensemble, line , end, trace, threadpool, regex, sauv, currentEvent);
			ensemble=null;
		
		}
		
		else {
			
			ResultsWriter.write(ensemble, trace, sauv);
			sauv.nbconv+=1;
			if (end) {
				threadpool.shut();
			}
		}
		return ;
	
	}
	
	public static void createNewConversationSetIfPossible(ConversationSet ensemble, int line , Boolean end, Trace trace, ThreadExecutor threadpool, Regex regex,Save sauv, Event currentEvent) {
		for (Conversation conver : ensembleOfConversations) {
			ArrayList<ArrayList<String>> Intersection= RouteConversationsSet.intersection(currentEvent, conver);
			
			if (Invariant.invariantintersect(Intersection)) {
				
				for (ArrayList<String> keysForIntersection : Intersection) {
					boolean verificationAllConversations=true;
					Conversation newConv = new Conversation(conver,keysForIntersection, currentEvent);
					ConversationSet newConvSet = new ConversationSet(ensemble,conver,newConv, keysForIntersection, currentEvent);
					for (Conversation conversationVerif : newConvSet.ConvSet) {
						if ((!Invariant.Invariant2(newConvSet, conversationVerif)) || (!Invariant.Invariant3(newConvSet,conversationVerif))) {
								verificationAllConversations = false;
						}
					}
					
					if(verificationAllConversations) {
						
						checkTresholdAndSubmitNewCreatedTask(new ConversationSet(ensemble,conver,new Conversation(conver,keysForIntersection, currentEvent), keysForIntersection, currentEvent), line , end, trace, threadpool,regex, sauv);
					}
				}
			}
		}
	}
	
	
	public static void tryNewConversationWithTheCurrentEvent(ConversationSet ensemble, int line , Boolean end, Trace trace, ThreadExecutor threadpool, Regex regex,Save sauv, Event currentEvent) {
		Conversation nouvelleconv2 = new Conversation(currentEvent);
		ensemble.ConvSet.add(nouvelleconv2);
		boolean invariantVerificationForAllConversations= true;
		for (Conversation conversationVerif : ensemble.ConvSet) {
			if ((!Invariant.Invariant2(ensemble, conversationVerif)) || (!Invariant.Invariant3(ensemble,conversationVerif))) {
					invariantVerificationForAllConversations = false;
				
			}
		}
		if(invariantVerificationForAllConversations) {
			ConversationSet newConvSetWithNewConversationConsistingOfTheNewEvent=new ConversationSet(ensemble);
			checkTresholdAndSubmitNewCreatedTask(newConvSetWithNewConversationConsistingOfTheNewEvent, line , end, trace, threadpool,regex, sauv);
		}
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


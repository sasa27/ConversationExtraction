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
	
	
	//méthode de test
	public static void find(ConversationSet currentConversationSet, int line , boolean end, Trace trace, ThreadExecutor threadpool, Regex regex,Save sauv){
		System.out.println(line);
		
		if(line!=trace.getSize()) {
			Event currentEvent=trace.getEvent(line);

			ensembleOfConversations = currentConversationSet.getConversationSet();
			
			if (line>=1) {
				createNewConversationSetIfPossible(currentConversationSet, line , end, trace, threadpool, regex, sauv, currentEvent);
			}
			tryNewConversationWithTheCurrentEvent(currentConversationSet, line , end, trace, threadpool, regex, sauv, currentEvent);
			currentConversationSet=null;
		
		}
		
		else {
			
			ResultsWriter.write(currentConversationSet, trace, sauv);
			/*if (end) {
				threadpool.shut();
			}*/
		}
		return ;
	
	}
	
	
	public static void createNewConversationSetIfPossible(ConversationSet currentConversationSet, int line , boolean end, Trace trace, ThreadExecutor threadpool, Regex regex,Save sauv, Event currentEvent) {
		for (Conversation conver : ensembleOfConversations) {
			ArrayList<ArrayList<String>> Intersection= RouteConversationsSet.intersection(currentEvent, conver);
			
			if (Invariant.invariantintersect(Intersection)) {
				
				for (ArrayList<String> keysForIntersection : Intersection) {
					Conversation newConv = new Conversation(conver,keysForIntersection, currentEvent);
					ConversationSet newConvSet = new ConversationSet(currentConversationSet,conver,newConv, keysForIntersection, currentEvent);
					
					
					
					if(verificationInvariants(newConvSet)) {
						
						checkTresholdAndSubmitNewCreatedTask(new ConversationSet(currentConversationSet,conver,new Conversation(conver,keysForIntersection, currentEvent), keysForIntersection, currentEvent), line , end, trace, threadpool,regex, sauv);
					}
				}
			}
		}
	}
	
	
	public static boolean verificationInvariants(ConversationSet verifThisConversationSet) {
		boolean invariantVerificationForAllConversations =
				verifThisConversationSet.ConvSet.stream().allMatch(conversationVerif ->
				  Invariant.Invariant2(verifThisConversationSet, conversationVerif) &&
				  Invariant.Invariant3(verifThisConversationSet,conversationVerif)
				);
		return invariantVerificationForAllConversations;
				
	}
	
	public static void tryNewConversationWithTheCurrentEvent(ConversationSet ensemble, int line , boolean end, Trace trace, ThreadExecutor threadpool, Regex regex,Save sauv, Event currentEvent) {
		Conversation nouvelleconv2 = new Conversation(currentEvent);
		ensemble.ConvSet.add(nouvelleconv2);
		if(verificationInvariants(ensemble)) {
			ConversationSet newConvSetWithNewConversationConsistingOfTheNewEvent=new ConversationSet(ensemble);
			checkTresholdAndSubmitNewCreatedTask(newConvSetWithNewConversationConsistingOfTheNewEvent, line , end, trace, threadpool,regex, sauv);
		}
	}
	
	
	
	
	public static void checkTresholdAndSubmitNewCreatedTask(ConversationSet ensemble, int ligne , boolean end, Trace trace, ThreadExecutor threadpool, Regex regex,Save sauv) {
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




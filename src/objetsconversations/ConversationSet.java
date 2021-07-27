package objetsconversations;
import java.io.File;



import java.util.*;

import metrique.Metric;

public class ConversationSet implements Cloneable {
	public ArrayList<Conversation> ConvSet;

	public ConversationSet() {
		ConvSet=new ArrayList<Conversation>();
		
	}
	
	
	
	public ConversationSet(ConversationSet Set) {
		ConvSet=new ArrayList<Conversation>();
		for(Conversation conv : Set.ConvSet) {
			ConvSet.add(new Conversation(conv));
		}
		
	}
	
	public ConversationSet(ConversationSet Set,ArrayList<String> key, Event event) {
		ConvSet=new ArrayList<Conversation>();
		for(Conversation conv : Set.ConvSet) {
			ConvSet.add(new Conversation(conv));
		}
		
	}


	public ConversationSet(ConversationSet Set, Conversation conv, Conversation conv2, ArrayList<String> nouvellecle,Event reqorrep) {
		
		ConvSet=new ArrayList<Conversation>();
		for(Conversation conver : Set.ConvSet) {
			if (!conver.getConv().equals(conv.getConv())) {
				ConvSet.add(new Conversation(conver));
			}
			else {
				ConvSet.add(new Conversation(conv2));
			}
		}
		
		
	}


	
	public ConversationSet(Conversation Conversation) {
		
		setConversationSet(new ArrayList<Conversation>());
		getConversationSet().add( Conversation);
	}

	
	public int getSize() {
		return this.ConvSet.size();
	}
	

	
	
	public ArrayList<Conversation> getConversationSet() {
		return this.ConvSet;
	}


	
	
	public void setConversationSet (ArrayList<Conversation> ensembleconv) {
		this.ConvSet = ensembleconv;
	}

	
	
	public static void setEnsembleConversation(Conversation Conversation, ConversationSet theSet){
		theSet.getConversationSet().add(Conversation);
		
	}

	
	
	
	//retourne l'heuristique d'un evenement
	public float getHeuristique(ConversationSet ensemble){
        return Metric.GetMetriquePersonnel(ensemble);
    }

	

	
	
	
	public ArrayList<ArrayList<Event>> getTrace(){
		ArrayList<ArrayList<Event>> renvoi = new ArrayList<ArrayList<Event>>();
		for (Conversation conv : this.ConvSet) {
			ArrayList<Event> rajout=new ArrayList<Event>();
			for (Event event : conv.getConv()) {
				rajout.add(event);
			}
			renvoi.add(rajout);
		}
		return renvoi;
	}
	
	public ArrayList<Set<ArrayList<String>>> getAllKeys(){
		ArrayList<Set<ArrayList<String>>> allKeys=new ArrayList<Set<ArrayList<String>>>();
		for(Conversation conv : this.ConvSet) {
			allKeys.add(new HashSet<ArrayList<String>>(conv.getChoosedKeys()));
		}
		return allKeys;
	}
	
	public static Set<String> getAllAssignments(ConversationSet convSet, ArrayList<Set<String>> allKeys){
		Set<String> allAssignments=new HashSet<String>();
		for(Conversation conv : convSet.getConversationSet()) {
			ArrayList<String> Array=new ArrayList<String>(conv.assignments);
			for(String assignInConv : Array) {
				String assignKey = assignInConv.split("=")[0];
				boolean tryAllKeys=false;
				for (Set<String> keyOneByOne : allKeys) {
					if (keyOneByOne.contains(assignKey)) {
						tryAllKeys=true;
					}
				}
				if(!tryAllKeys) {
					allAssignments.add(assignKey);
				}
			}
		}
		return allAssignments;
	}
	
	
}
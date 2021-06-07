package objetsconversations;
import java.io.File;


import java.util.*;

import metrique.MetriquePersonnel;

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

	
	
	public static void setEnsembleConversation(Conversation Conversation, ConversationSet ensembledumoment){
		ensembledumoment.getConversationSet().add(Conversation);
		
	}

	
	
	
	//retourne l'heuristique d'un evenement
	public float getHeuristique(ConversationSet ensemble){
        return MetriquePersonnel.GetMetriquePersonnel(ensemble);
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
	
	
}
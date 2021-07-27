package objetsconversations;

import java.util.ArrayList;
import java.util.Collections;



public class Conversation{
  public ArrayList<Event> conv;

  public ArrayList<ArrayList<String>> choosedKeys;

  public ArrayList<String> assignments;
  public ArrayList<String> keys;

  public int nbRequest;
  public int nbResponse;
  
  public Conversation(){
      conv= new ArrayList<Event>();


      choosedKeys= new ArrayList<ArrayList<String>>();

      nbRequest=0;
      nbResponse=0;
      assignments= new ArrayList<String>();
      
      keys=new ArrayList<String>();;
  }

  public Conversation(Conversation conversation){
      conv= new ArrayList<Event>();
      for (Event ev : conversation.conv) {
    	  conv.add(ev);
      }
      
 
      choosedKeys= new ArrayList<ArrayList<String>>();
      for(ArrayList<String> choisi : conversation.choosedKeys) {
    	  choosedKeys.add(choisi);
      }
      nbRequest=conversation.nbRequest;
      nbResponse=conversation.nbResponse;
      assignments =  new ArrayList<String>();
      for (String assi : conversation.assignments) {
    	  assignments.add(assi);
      }
      keys=new ArrayList<String>();;
      
  }
  
  
  public Conversation(Conversation conversation, ArrayList<String> newKeys, Event reqorrep){
	  conv= new ArrayList<Event>();
      for (Event ev : conversation.conv) {
    	  conv.add(ev);
      }
      
      assignments =  new ArrayList<String>();
      for (String assi : conversation.assignments) {
    	  assignments.add(assi);
      }
      

      choosedKeys= new ArrayList<ArrayList<String>>();
      for(ArrayList<String> choisi : conversation.choosedKeys) {
    	  choosedKeys.add(choisi);
      }
      
      for(String param : reqorrep.getparams()) {
    	  if (!assignments.contains(param)) {
    		  assignments.add(param);
    	  }
      }
      if (reqorrep.isReq()) {
    	  nbRequest=conversation.getReq()+1;
    	  nbResponse=conversation.getRep();
      }
      else { if (reqorrep.isResp()) {
    	  nbRequest=conversation.getReq();
    	  nbResponse=conversation.getRep()+1;
      }
      else {
    	  nbRequest=conversation.getReq();
    	  nbResponse=conversation.getRep();
      }
      }
      keys=newKeys;
      choosedKeys.add(newKeys);
      
      conv.add(reqorrep);
  }
  
  
  public Conversation(Event event){
    conv= new ArrayList<Event>();
    assignments =  new ArrayList<String>();
    for(String param : event.getparams()) {
  	  if (!assignments.contains(param)) {
  		  assignments.add(param);
  	  }
    }
    choosedKeys=new ArrayList<ArrayList<String>>();

    nbRequest=0;
    nbResponse=0;
    keys=new ArrayList<String>();
    conv.add(event);
    if (event.isReq()) {
		nbRequest++;
	}
    if (event.isResp()){
		nbResponse++;
	}
   
  }
  
 

  //renvoie la taille de la conversation
  public int size() {
      return this.conv.size();
  }
  public void NouvelEvent(Event evenement ) {
    this.conv.add(evenement);
    
    if (evenement.isReq()) {
    	
    	this.nbRequest+=1;
    }
    else {
    	this.nbResponse+=1;
    }
  }

  public void setconv(Conversation conv2) {
		this.conv = conv2.conv;
	}
  

  

  //renvoie la conversation
  public ArrayList<Event> getConv(){
     //Collections.unmodifiableList(conv);
	  return conv;
  }

  //renvoie le dernier evenement d'une conversation
  public Event getLastEvent(){
		return this.conv.get(this.conv.size()-1);
	}
  public Event getFirstEvent(){
		return this.conv.get(0);
	}

  
  
  

  public ArrayList<ArrayList<String>> getChoosedKeys() {
	return choosedKeys;
	}

	public void addChoosedKeys(ArrayList<String> clechoisi) {
	this.choosedKeys.add(clechoisi);
	}
  

	public int getReq(){
		return this.nbRequest;
	}
	public int getRep(){
		return this.nbResponse;
	}
	
	
}

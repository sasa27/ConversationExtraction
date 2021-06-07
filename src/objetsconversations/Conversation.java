package objetsconversations;

import java.util.ArrayList;
import java.util.Collections;



public class Conversation{
  public ArrayList<Event> conv;

  public ArrayList<ArrayList<String>> clechoisi;

  public ArrayList<String> Assignements;
  public ArrayList<String> cle;

  public int nbrequete;
  public int nbreponse;
  
  public Conversation(){
      conv= new ArrayList<Event>();


      clechoisi= new ArrayList<ArrayList<String>>();

      nbrequete=0;
      nbreponse=0;
      Assignements= new ArrayList<String>();
      
      cle=new ArrayList<String>();;
  }

  public Conversation(Conversation conversation){
      conv= new ArrayList<Event>();
      for (Event ev : conversation.conv) {
    	  conv.add(ev);
      }
      
 
      clechoisi= new ArrayList<ArrayList<String>>();
      for(ArrayList<String> choisi : conversation.clechoisi) {
    	  clechoisi.add(choisi);
      }
      nbrequete=conversation.nbrequete;
      nbreponse=conversation.nbreponse;
      Assignements =  new ArrayList<String>();
      for (String assi : conversation.Assignements) {
    	  Assignements.add(assi);
      }
      cle=new ArrayList<String>();;
      
  }
  
  
  public Conversation(Conversation conversation, ArrayList<String> nouvellescles, Event reqorrep){
	  conv= new ArrayList<Event>();
      for (Event ev : conversation.conv) {
    	  conv.add(ev);
      }
      
      Assignements =  new ArrayList<String>();
      for (String assi : conversation.Assignements) {
    	  Assignements.add(assi);
      }
      

      clechoisi= new ArrayList<ArrayList<String>>();
      for(ArrayList<String> choisi : conversation.clechoisi) {
    	  clechoisi.add(choisi);
      }
      
      for(String param : reqorrep.getparams()) {
    	  if (!Assignements.contains(param)) {
    		  Assignements.add(param);
    	  }
      }
      if (reqorrep.isReq()) {
    	  nbrequete=conversation.getReq()+1;
    	  nbreponse=conversation.getRep();
      }
      else { if (reqorrep.isResp()) {
    	  nbrequete=conversation.getReq();
    	  nbreponse=conversation.getRep()+1;
      }
      else {
    	  nbrequete=conversation.getReq();
    	  nbreponse=conversation.getRep();
      }
      }
      cle=nouvellescles;
      clechoisi.add(nouvellescles);
      
      conv.add(reqorrep);
  }
  
  
  public Conversation(Event event){
    conv= new ArrayList<Event>();
    Assignements =  new ArrayList<String>();
    for(String param : event.getparams()) {
  	  if (!Assignements.contains(param)) {
  		  Assignements.add(param);
  	  }
    }
    clechoisi=new ArrayList<ArrayList<String>>();

    nbrequete=0;
    nbreponse=0;
    cle=new ArrayList<String>();
    conv.add(event);
    if (event.isReq()) {
		nbrequete++;
	}
    if (event.isResp()){
		nbreponse++;
	}
   
  }
  
 

  //renvoie la taille de la conversation
  public int size() {
      return this.conv.size();
  }
  public void NouvelEvent(Event evenement ) {
    this.conv.add(evenement);
    
    if (evenement.isReq()) {
    	
    	this.nbrequete+=1;
    }
    else {
    	this.nbreponse+=1;
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

  
  
  

  public ArrayList<ArrayList<String>> getClechoisi() {
	return clechoisi;
	}

	public void addClechoisi(ArrayList<String> clechoisi) {
	this.clechoisi.add(clechoisi);
	}
  

	public int getReq(){
		return this.nbrequete;
	}
	public int getRep(){
		return this.nbreponse;
	}
	
	
}

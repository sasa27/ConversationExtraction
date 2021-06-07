package metrique;

import java.util.ArrayList;


import java.util.List;

import objetsconversations.*;

public class MetriquePersonnel {

    //renvoie un metrique composé du nombre d'événements - le nombre d'evenements seuls
	public int compteurthreadsMorts;
	
	
    
    public static float GetMetriquePersonnel(ConversationSet ensemble) {
    	ArrayList<Conversation> ensembleconv = new ArrayList<Conversation>(ensemble.getConversationSet());
    	float qualiterequete=0;
    	float qualitereponse=0;
    	float qualitetotale=0;
    	int nombrereq=0;
    	int nombrerep=0;
    	float commence=0;
    	float compteur=0;
    	for (Conversation conv : ensembleconv) {
    		compteur+=1;

    		Event premierevent=conv.getFirstEvent();
        	if (premierevent.isReq()) {
        		commence+=1;
        	}
        	if (conv.size()>2) {
    			if (conv.getReq()>=conv.getRep()){
    				qualiterequete+=conv.getRep();
    				
    				qualitereponse+=conv.getRep();
    				nombrereq+=conv.getReq();
        			nombrerep+=conv.getRep();
    			}
    			else {
    				qualiterequete+=conv.getReq();
    				qualitereponse+=conv.getReq();
    				nombrereq+=conv.getReq();
        			nombrerep+=conv.getRep();
    			}
        	}else {
        		qualiterequete+=1;
				qualitereponse+=1;
				nombrereq+=1;
    			nombrerep+=1;
        	}
        	
    		
    			
    	}
    		qualiterequete=qualiterequete/nombrereq;
			qualitereponse=qualitereponse/nombrerep;
    	
    		
			
    	commence=commence/compteur;
    	qualitetotale=(qualiterequete+qualitereponse+commence)/3;
    	
    	return qualitetotale;
    
    }
    

    
    /*
    public static float qualite3() {
    	return
    }*/
}

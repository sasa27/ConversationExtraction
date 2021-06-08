package metrique;

import java.util.ArrayList;


import java.util.List;

import objetsconversations.*;

public class Metric {

    //renvoie un metrique composé du nombre d'événements - le nombre d'evenements seuls
	public int compteurthreadsMorts;
	
	
    
    public static float GetMetriquePersonnel(ConversationSet ensemble) {
    	ArrayList<Conversation> ensembleconv = new ArrayList<Conversation>(ensemble.getConversationSet());
    	float qualityRequest=0;
    	float qualityResponse=0;
    	float qualityTotal=0;
    	int numberReq=0;
    	int numberResp=0;
    	float startingByRequest=0;
    	float numberOfConversations=0;
    	for (Conversation conv : ensembleconv) {
    		numberOfConversations+=1;

    		Event premierevent=conv.getFirstEvent();
        	if (premierevent.isReq()) {
        		startingByRequest+=1;
        	}
        	if (conv.size()>2) {
    			if (conv.getReq()>=conv.getRep()){
    				qualityRequest+=conv.getRep();
    				
    				qualityResponse+=conv.getRep();
    				numberReq+=conv.getReq();
        			numberResp+=conv.getRep();
    			}
    			else {
    				qualityRequest+=conv.getReq();
    				qualityResponse+=conv.getReq();
    				numberReq+=conv.getReq();
        			numberResp+=conv.getRep();
    			}
        	}else {
        		qualityRequest+=1;
				qualityResponse+=1;
				numberReq+=1;
    			numberResp+=1;
        	}
        	
    		
    			
    	}
    		qualityRequest=qualityRequest/numberReq;
			qualityResponse=qualityResponse/numberResp;
    	
    		
			
    	startingByRequest=startingByRequest/numberOfConversations;
    	qualityTotal=(qualityRequest+qualityResponse+startingByRequest)/3;
    	
    	return qualityTotal;
    
    }
    
    public static float ReqwResp(ConversationSet ensemble) {
    	ArrayList<Conversation> ensembleconv = new ArrayList<Conversation>(ensemble.getConversationSet());
    	int numberReq=0;
    	float qualityRequest=0;
    	for (Conversation conv : ensembleconv) {
    		if (conv.size()>2) {
    			if (conv.getReq()>conv.getRep()){
    				numberReq=conv.getReq();
    				qualityRequest+=conv.getRep();
    			}
    			else {
    				numberReq=conv.getReq();
    				qualityRequest+=conv.getReq();
    			}
    		}
    	}
    	qualityRequest=qualityRequest/numberReq;
    	return qualityRequest;
    }
    
    public static float RespwReq(ConversationSet ensemble) {
    	ArrayList<Conversation> ensembleconv = new ArrayList<Conversation>(ensemble.getConversationSet());
    	int numberRep=0;
    	float qualityResponse=0;
    	for (Conversation conv : ensembleconv) {
    		if (conv.size()>2) {
    			if (conv.getRep()>conv.getReq()){
    				numberRep=conv.getRep();
    				qualityResponse+=conv.getReq();
    			}
    			else {
    				numberRep=conv.getRep();
    				qualityResponse+=conv.getRep();
    			}
    		}
    	}
    	qualityResponse=qualityResponse/numberRep;
    	return qualityResponse;
    }

    public static float startByARequest(ConversationSet ensemble) {
    	ArrayList<Conversation> ensembleconv = new ArrayList<Conversation>(ensemble.getConversationSet());
    	float startingByRequest=0;
    	float numberOfConversations=0;
    	numberOfConversations+=1;
    	for (Conversation conv : ensembleconv) {
			Event premierevent=conv.getFirstEvent();
	    	if (premierevent.isReq()) {
	    		startingByRequest+=1;
	    	}
    	}
    	return startingByRequest/numberOfConversations;
    }
    
    /*
    public static float qualite3() {
    	return
    }*/
}

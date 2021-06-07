package conditions;
import java.util.ArrayList;

import objetsconversations.*;


//implémenter un parser du premier ordre pour les conditions
public class KeyCondition {
    public static boolean verifyCle(ConversationSet set, Conversation conv, Event event){
        ArrayList<String> listparam= event.getparams();
        ArrayList<String> listeparamlastevent=conv.getClesRestantes();
        for (String parametre : listparam) {
        		if (listeparamlastevent.contains(parametre) && set.UnauthorizedParam(parametre, set)){
        	
            	
        			return true;
        		}
        	}
        
    	return false;
    }
    
  }


/**condition de la clé : pas utiliser dans une autre conversation : liste des clés déjà utilisé lorsqu'on en choisit une
 * pour une nouvelle comm : liste clé utilisée, deuxieme liste : clées pouvant être utilisées avec la nouvelle conv une fois qu'on en
 * met un deuxieme dans la conversation : on met toutes les clés dans utilisées et on garde celle choisie pour l'utilisation
 * pour celle choisie pour l'utilisation : une fois qu'un deuxieme event entre dans la conversation : on crée une nouvelle conversation
 * pour chaque clée possible.
 *  * */
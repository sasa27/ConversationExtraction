package objetsconversations;
import java.util.*;
public class Invariant {
	//public static boolean invariant(ConversationSet ensemble, Event evenement) {
		/*
		for (String parametre : evenement.getparams) {
			if (!ensemble.UnauthorizedKeys.contains(params)) {
				return true;
			}
		}
		return false;
		*/
		//return true;
	//}

	public static boolean invariantintersect(ArrayList<ArrayList<String>> liste) {
			return liste.size()!=0;
	}
	

	public static boolean invariantrequete(Event event) {
		return event.isReq();
	}
	

	
	public static boolean invariantFromTo(Event event, Conversation conver) {
		
		Event dernierevent=conver.getLastEvent();
		if (dernierevent.getTo().equals(event.getFrom())) {
			return true;
		}
		return false;
	}
	
	

	




	public static boolean Invariant2(ConversationSet ensemble, Conversation conv) {
		Set<String> SetParam= new LinkedHashSet<>();
		for (Conversation conver : ensemble.ConvSet) {
			if (!conver.getConv().equals(conv.getConv())) {
				SetParam.addAll(conver.Assignements);
			}
		}
		ArrayList<String> lesAssignements = new ArrayList<String>(SetParam);
		for (ArrayList<String> CleParam : conv.clechoisi) {
			for (String assignement : lesAssignements) {
				if (CleParam.contains(assignement)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean Invariant3(ConversationSet ensemble, Conversation conv) {
		Set<String> SetParam= new LinkedHashSet<>();
		for (Conversation conver : ensemble.ConvSet) {
			if (!conver.getConv().equals(conv.getConv())) {
				SetParam.addAll(conver.Assignements);
			}
		}
		ArrayList<String> lesAssignements = new ArrayList<String>(SetParam);
		Set<String> Assignementsencours= new LinkedHashSet<>(conv.Assignements);
		for (String ass : Assignementsencours) {;
			if (!lesAssignements.contains(ass)) {
				return true;
			}
		}
		return false;
		
	}
	
}


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
	

	public static boolean invariantRequest(Event event) {
		return event.isReq();
	}
	

	
	public static boolean invariantFromTo(Event event, Conversation conver) {
		
		Event lastEvent=conver.getLastEvent();
		if (lastEvent.getTo().equals(event.getFrom())) {
			return true;
		}
		return false;
	}
	
	

	




	public static boolean Invariant2(ConversationSet ensemble, Conversation conv) {
		Set<String> setOfParameters= new LinkedHashSet<>();
		for (Conversation conver : ensemble.ConvSet) {
			if (!conver.getConv().equals(conv.getConv())) {
				setOfParameters.addAll(conver.assignments);
			}
		}
		ArrayList<String> theAssignments = new ArrayList<String>(setOfParameters);
		for (ArrayList<String> key : conv.choosedKeys) {
			for (String assignment : theAssignments) {
				if (key.contains(assignment)) {
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
				SetParam.addAll(conver.assignments);
			}
		}
		ArrayList<String> theAssignments = new ArrayList<String>(SetParam);
		Set<String> assignmentsFromThisConv= new LinkedHashSet<>(conv.assignments);
		for (String assignementOnebyOne : assignmentsFromThisConv) {;
			if (!theAssignments.contains(assignementOnebyOne)) {
				return true;
			}
		}
		return false;
		
	}
	
}


package objetsconversations;
import java.util.*;
/**
* Contain every invariant used
*/
public class Invariant {

	/**
	* return if the list is empty or not
	*
	* @param  liste  represent the list which is gonna be looked on
	* @return    boolean
	*/
	public static boolean invariantintersect(ArrayList<ArrayList<String>> liste) {
			return liste.size()!=0;
	}
	
	/**
	* Return if the Event is a request or not
	*
	* @param  event Event verified
	* @return   boolean
	*/
	public static boolean invariantRequest(Event event) {
		return event.isReq();
	}
	
	/**
	* Return if the last event is an event directed to the first event of the conversation
	*
	* @param  event Event verified
	* @param  conver Conversation from which the Events are gonna be verified
	* @return   boolean
	*/
	
	public static boolean invariantFromTo(Event event, Conversation conver) {
		
		Event lastEvent=conver.getLastEvent();
		if (lastEvent.getTo().equals(event.getFrom())) {
			return true;
		}
		return false;
	}
	
	

	


	/**
	* Return if there is assigments which 
	*
	* @param  event Event verified
	* @return   boolean
	*/

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


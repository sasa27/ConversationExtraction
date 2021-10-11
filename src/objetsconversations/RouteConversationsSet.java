package objetsconversations;
import java.util.ArrayList;


public class RouteConversationsSet {
	
	

	
	

	public static ArrayList<ArrayList<String>> combinationsOfEvent(ArrayList<String> event){
		ArrayList<ArrayList<String>> keysCombinations = Combinations.getCombinations(event);
		keysCombinations.remove(0);
	    return keysCombinations;
	}
	
	
	
	public static ArrayList<ArrayList<String>> intersection(Event currentEvent, Conversation conversat){

		
		ArrayList<String> conv = new ArrayList<String>();
		for (String ev :conversat.getLastEvent().getParamsWithoutFromTo()) {
			conv.add(ev);
		}
		ArrayList<String> event =currentEvent.getParamsWithoutFromTo();

		conv.retainAll(event);

		ArrayList<ArrayList<String>> combinationsStringLastEvent= combinationsOfEvent(conv);

	    return combinationsStringLastEvent;
	    
		
	}
	
}

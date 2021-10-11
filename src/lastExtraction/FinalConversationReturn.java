package lastExtraction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import objetsconversations.Event;

public class FinalConversationReturn {
	private ArrayList<Event> conv;
	private Map<String, String> choosedKeys;
	public FinalConversationReturn(Event event){
		conv= new ArrayList<Event>();
		conv.add(event);
		choosedKeys=new HashMap<String, String>();
		for (String param :  event.getParamsWithoutFromTo()) {
			if(param.contains("=")) {
				String[] parts = param.split("=");
				choosedKeys.put(parts[0],parts[1].toString());
			}
			
		}
	}
	
	public void addEvent(Event event) {
		conv.add(event);
		choosedKeys=new HashMap<String, String>();
		for (String param :  event.getParamsWithoutFromTo()) {
			String[] parts = param.split("=");
			choosedKeys.put(parts[0],parts[1].toString());
		}
	}

	public ArrayList<Event> getConv() {
		return conv;
	}

	public void setConv(ArrayList<Event> conv) {
		this.conv = conv;
	}

	public Map<String, String> getChoosedKeys() {
		return choosedKeys;
	}

	public void setChoosedKeys(Map<String, String> choosedKeys) {
		this.choosedKeys = choosedKeys;
	}
}

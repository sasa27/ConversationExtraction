package algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import objetsconversations.*;
import split.Trace;


public class Extraction {
	public static ArrayList<Event> extractionWithKey(ArrayList<ArrayList<String>> chooseKeys, Trace trace){
		ArrayList<Event> extracted = new ArrayList<>();
		for(Event event : trace.seq) {
			for(ArrayList<String> cle : chooseKeys) {
				if(event.getparams().contains(cle.toString())) {
					extracted.add(event);
				}
			}
		}
		return extracted;
	}
	
	public static ArrayList<Event> extractionWithKeyByConversation(ArrayList<ArrayList<String>> chooseKeys, Trace trace){
		Map<String, ArrayList<Event>> extractedListOfConversations = new HashMap<>();
		ArrayList<Event> extractedEvents = new ArrayList<>();
		for(Event event : trace.seq) {
			for(ArrayList<String> cle : chooseKeys) {
				if(event.getparams().contains(cle.toString())) {
					extractedEvents.add(event);
				}
			}
		}
		return extractedEvents;
	}
	
	
	/*
	public static ArrayList<ArrayList<Event>> extractionWithMultipleKeys(ArrayList<ArrayList<String>> chooseKeys,Trace trace){
		for 
	}
	*/
}

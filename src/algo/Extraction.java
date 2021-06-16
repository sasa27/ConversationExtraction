package algo;

import java.util.ArrayList;
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
	
	/*
	public static ArrayList<ArrayList<Event>> extractionWithMultipleKeys(ArrayList<ArrayList<String>> chooseKeys,Trace trace){
		for 
	}
	*/
}

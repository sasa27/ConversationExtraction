package keyDecision;
import java.io.*;
import java.util.*;

import objetsconversations.Conversation;
import objetsconversations.Event;
public class KeysFound {
	public ArrayList<Set<String>> keys;
	public Set<String> unauthorizedKeys;
	public KeysFound() {
		keys= new ArrayList<Set<String>>();
		unauthorizedKeys=new HashSet<String>();
	}
	
	public KeysFound(KeysFound founded,KeysFound newFounded){
		  keys= new ArrayList<Set<String>>(founded.keys);
		  for (Set<String> newKeys: newFounded.keys) {
				 keys.add(newKeys);
			  
		  }
		 
		  unauthorizedKeys=new HashSet<String>(founded.unauthorizedKeys);
		  for (String newKeys: newFounded.unauthorizedKeys) {
			  if(!unauthorizedKeys.contains(newKeys)){
				  unauthorizedKeys.add(newKeys);
			  }
		  }
	  }
	
	public KeysFound(KeysFound founded){
		  keys= new ArrayList<Set<String>>(founded.keys);
		  unauthorizedKeys=new HashSet<String>(founded.unauthorizedKeys);
	  }
	
	
	public static Comparator<KeysFound> ComparatorFirstString = new Comparator<KeysFound>() {
	      
        @Override
        public int compare(KeysFound e1, KeysFound e2) {
        	ArrayList<String> firstListToCompare=new ArrayList<>(e1.keys.get(0));
        	ArrayList<String> secondListToCompare=new ArrayList<>(e2.keys.get(0));
            return (firstListToCompare.get(0)).compareTo(secondListToCompare.get(0));
        }
	};
	 
	
}

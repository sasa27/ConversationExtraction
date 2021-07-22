package keyDecision;
import java.io.*;
import java.util.*;
public class KeysFound {
	public ArrayList<Set<String>> keys;
	public ArrayList<Set<String>> unauthorizedKeys;
	public KeysFound() {
		keys= new ArrayList<Set<String>>();
		unauthorizedKeys=new ArrayList<Set<String>>();
	}
	
	public Set<String> unauthorizedKeysFind(int thisPlace){
		return this.unauthorizedKeys.get(thisPlace);
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

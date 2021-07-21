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
}

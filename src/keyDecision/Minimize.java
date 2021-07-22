package keyDecision;
import java.util.Collections;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import objetsconversations.*;

public class Minimize {
	public static GroupOfKeys minimize(GroupOfKeys group) {
		ArrayList<KeysFound> allKeys = new ArrayList<KeysFound>(group.groupOfKeysFound);
		Collections.sort(allKeys, KeysFound.ComparatorFirstString);
		ArrayList<ArrayList<Set<String>>> alreadyChoosed= new ArrayList<ArrayList<Set<String>>>();
		ArrayList<KeysFound> minimizedKeys = new ArrayList<KeysFound>();
		if (!allKeys.isEmpty()) {
			for(int i=0; i<allKeys.size(); i++) {
				KeysFound setOfKeys=allKeys.get(i);
				if(!alreadyChoosed.containsAll(setOfKeys.keys)) {
					alreadyChoosed.add(setOfKeys.keys);
					minimizedKeys.add(setOfKeys);
				}
				
			}
		}
		GroupOfKeys mimizedGroup=new GroupOfKeys(minimizedKeys);
		return mimizedGroup;
		//écrire dans un fichier le résultat
	}
}

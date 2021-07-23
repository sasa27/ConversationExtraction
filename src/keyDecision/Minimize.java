package keyDecision;
import java.util.Collections;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import objetsconversations.*;

public class Minimize {
	public static void minimize(GroupOfKeysInOneFile file, GroupOfAllFiles group) {
		ArrayList<KeysFound> allKeys = new ArrayList<KeysFound>(file.groupOfKeysFound);
		Collections.sort(allKeys, KeysFound.ComparatorFirstString);
		ArrayList<ArrayList<Set<String>>> alreadyChoosed= new ArrayList<ArrayList<Set<String>>>();
		ArrayList<KeysFound> minimizedKeys = new ArrayList<KeysFound>();
		if (!allKeys.isEmpty()) {
			for(int i=0; i<allKeys.size(); i++) {
				KeysFound setOfKeys=allKeys.get(i);
				boolean isHere=false;
				for (ArrayList<Set<String>> toVerified : alreadyChoosed) {
					if(toVerified.containsAll(setOfKeys.keys)) {
						isHere=true;
					}
				}
				if (!isHere) {
					alreadyChoosed.add(setOfKeys.keys);
					minimizedKeys.add(setOfKeys);
				}
				
			}
		}
		GroupOfKeysInOneFile minimizedGroup=new GroupOfKeysInOneFile(minimizedKeys);
		group.groupOfAllFiles.add(minimizedGroup);
		//�crire dans un fichier le r�sultat
	}
}

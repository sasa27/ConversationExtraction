package keyDecision;
import java.util.Collections;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import objetsconversations.*;

public class Minimize {
	public KeysFound minimize(KeysFound keysInOneFile) {
		KeysFound minimizedKeys = new KeysFound();
		if (!keysInOneFile.keys.isEmpty()) {
			ArrayList<String> listToSort = new ArrayList<String>();
			for (Set Key : keysInOneFile.keys) {
				listToSort.add(Key.toString());
			}
			Collections.sort(listToSort);
			List<String> list = new ArrayList<String>(hset);
			foreach(String keys : keysInOneFile.Keys){
				foreach(String key : keys){
					if(!minimizedKeys.contains(key)) {
						minimizedKeys.Keys.add(keys);
						minimizedKeys.UnauthorizedKeys.add(S.unauthorizedKeysFind(keys));
					}
				}
			}
		}
		//écrire dans un fichier le résultat
	}
}

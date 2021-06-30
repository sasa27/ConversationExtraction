package keyDecision;
import objetsconversations.*;

public class Minimize {
	public KeysFound minimize(KeysFound keysInOneFile) {
		KeysFound minimizedKeys = new KeysFound();
		if (!keysInOneFile.keys.isEmpty()) {
			keysInOneFile.keys.alphabeticalSort();
			foreach(keys in keysInOneFile.Keys){
				foreach(keys==emptyset){
					minimizedKeys.Keys.add(keys);
					minimizedKeys.UnauthorizedKeys.add(S.unauthorizedKeysFind(keys));
					return minimizedKeys;
				}
			}
		}
		else{
			Stop Pool
		}
	}
}

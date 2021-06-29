package keyDecision;
import objetsconversations.*;

public class Minimize {
	public KeysFound minimize(KeysFound keysInOneFile) {
		KeysFound minimizedKeys = new KeysFound();
		if (keysInOneFile.keys!=[]) {
			keysInOneFile.Keys.alphabeticalSort();
			foreach(keys in keysInOneFile.Keys){
				foreach(keys==em ptyset){
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

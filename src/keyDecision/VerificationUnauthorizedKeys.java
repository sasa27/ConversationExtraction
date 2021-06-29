package keyDecision;
import java.util.*;

import objetsconversations.KeysFound;

public class VerificationUnauthorizedKeys {
	public static boolean VerificationUnauthorizedKeysProcedure(ArrayList<Set<String>> keysInOneFile, Set<Set<String>> listUnauthorizedKeysNow) {
		for (Set<String> verificationOfAKey : keysInOneFile) {
			if(listUnauthorizedKeysNow.contains(verificationOfAKey)){
				return false;
			}
		}
		
			return true;
		
	}
}

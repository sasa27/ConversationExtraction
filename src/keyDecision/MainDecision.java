package keyDecision;

public class MainDecision {
	public findingKeys(KeysFound S,Set<Set<String>> ListKeyNow,Set<Set<String>> listUnauthorizedKeysNow, SimpleThreadPool pool, int position) {
		if (S is not empty) {
			foreach(key in S){
				if(VerificationUnauthorizedKeys(key.unauthorizedKeys,listUnauthorizedKeysNow)){
					thenListKeyNow.add(key);
					listUnauthorizedKeysNow.add(key.unauthorizedKeys){
					Add FindingTask(S, ListKeyNow, listUnauthorizedKeysNow, positionofSinGroup+1) to Pool
					}
				}
			}
			
		}
	}
}

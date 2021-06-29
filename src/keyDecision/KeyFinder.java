package keyDecision;
import java.util.*;
import objetsconversations.*;
import parralelisation.*;
public class KeyFinder {
	public void findingKeys(GroupOfKeys Group ,Set<Set<String>> ListKeyNow,Set<Set<String>> listUnauthorizedKeysNow, SimpleThreadPool pool, int position) {
		KeysFound foundKeysInOneFile=Group.getgroupOfKeys(position);
		if (foundKeysInOneFile.keys.size()!=0) {
			for(Set<String> key : foundKeysInOneFile.keys){
				if(VerificationUnauthorizedKeys.VerificationUnauthorizedKeysProcedure(foundKeysInOneFile.unauthorizedKeys,listUnauthorizedKeysNow)){
					ListKeyNow.add(key);
					listUnauthorizedKeysNow.addAll(foundKeysInOneFile.unauthorizedKeys);
					pool.SubmitTask(TaskFinder(Group, ListKeyNow, listUnauthorizedKeysNow, position+1);
					
				}
			}
			
		}
	}
}

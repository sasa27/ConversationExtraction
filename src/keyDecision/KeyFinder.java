package keyDecision;
import java.util.*;
import objetsconversations.*;
import parralelisation.*;
public class KeyFinder {
	public static void findingKeys(GroupOfKeys Group ,Set<Set<String>> ListKeyNow,Set<Set<String>> listUnauthorizedKeysNow, ThreadExecutorSimpleBlockingQueue pool, int position) {
		KeysFound foundKeysInOneFile=Group.getgroupOfKeys(position);
		if (foundKeysInOneFile.keys.size()!=0) {
			for(Set<String> key : foundKeysInOneFile.keys){
				if(VerificationUnauthorizedKeys.VerificationUnauthorizedKeysProcedure(foundKeysInOneFile.unauthorizedKeys,listUnauthorizedKeysNow)){
					ListKeyNow.add(key);
					listUnauthorizedKeysNow.addAll(foundKeysInOneFile.unauthorizedKeys);
					TaskFinder task = new TaskFinder(Group, ListKeyNow, listUnauthorizedKeysNow,pool, position+1);
					pool.SubmitTask(task);
					
				}
			}
			
		}
	}
}

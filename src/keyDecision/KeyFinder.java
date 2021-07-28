package keyDecision;
import java.util.*;

import objetsconversations.*;
import parralelisation.*;
public class KeyFinder {
	public static synchronized void findingKeys(GroupOfAllFilesMinimized allFilesGroup, KeysFound Group , ThreadExecutorFinding pool, int position, Set<HashSet<Set<String>>> result) {
		if(!(position+1==allFilesGroup.groupOfAllFiles.size())) {
			GroupOfKeysInOneFile allInOneFile=allFilesGroup.groupOfAllFiles.get(position);
			GroupOfKeysInOneFile newGroupWhenKeysInCommon=new GroupOfKeysInOneFile();
			for(KeysFound keysToWorkWith: allInOneFile.groupOfKeysFound) {
				if (!Collections.disjoint(keysToWorkWith.keys, Group.keys)) {
					newGroupWhenKeysInCommon.groupOfKeysFound.add(new KeysFound(keysToWorkWith));
				}
			}
			if (!newGroupWhenKeysInCommon.groupOfKeysFound.isEmpty()) {
				allInOneFile=newGroupWhenKeysInCommon;
			}
			for(KeysFound keysToWorkWith: allInOneFile.groupOfKeysFound){
				if(VerificationUnauthorizedKeys.VerificationUnauthorizedKeysProcedure(Group,keysToWorkWith)){
					TaskFinder task = new TaskFinder(allFilesGroup, new KeysFound (Group, keysToWorkWith),pool, position+1);
					pool.SubmitFindingTask(task);
				}
				
			}
		}
		
		
		
		
		else {
			result.add(new HashSet<Set<String>>(Group.keys));
		}
	}
}

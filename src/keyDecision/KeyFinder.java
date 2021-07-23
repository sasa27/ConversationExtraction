package keyDecision;
import java.util.*;

import objetsconversations.*;
import parralelisation.*;
public class KeyFinder {
	public static void findingKeys(GroupOfAllFiles allFilesGroup, KeysFound Group , ThreadExecutorSimpleBlockingQueue pool, int position) {
		
		if(!(position==allFilesGroup.groupOfAllFiles.size())) {
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
			System.out.println(Group.keys);
		}
	}
}

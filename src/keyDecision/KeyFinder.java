package keyDecision;
import java.util.*;

import objetsconversations.*;
import parralelisation.*;
public class KeyFinder {
	/**
	* Find the keys which can be used as global keys
	* @param  allFilesGroup
	* @param  group
	* @param  pool
	* @param  position
	* @param  allFilesGroup
	* @return      void
	*/
	public static synchronized void findingKeys(GroupOfAllFilesMinimized allFilesGroup, KeysFound group , ThreadExecutorFinding pool, int position, Set<HashSet<Set<String>>> result) {
		System.out.println("par ici");
		System.out.println(position);
		if(!(position+1==allFilesGroup.groupOfAllFiles.size())) {
			GroupOfKeysInOneFile allInOneFile=allFilesGroup.groupOfAllFiles.get(position);
			GroupOfKeysInOneFile newGroupWhenKeysInCommon=new GroupOfKeysInOneFile();
			for(KeysFound keysToWorkWith: allInOneFile.groupOfKeysFound) {
				if (!Collections.disjoint(keysToWorkWith.keys, group.keys)) {
					newGroupWhenKeysInCommon.groupOfKeysFound.add(new KeysFound(keysToWorkWith));
				}
			}
			if (!newGroupWhenKeysInCommon.groupOfKeysFound.isEmpty()) {
				allInOneFile=newGroupWhenKeysInCommon;
			}
			for(KeysFound keysToWorkWith: allInOneFile.groupOfKeysFound){
				if(VerificationUnauthorizedKeys.VerificationUnauthorizedKeysProcedure(group,keysToWorkWith)){
					TaskFinder task = new TaskFinder(allFilesGroup, new KeysFound (group, keysToWorkWith),pool, position+1);
					pool.SubmitFindingTask(task);
				}
				
			}
		}
		
		else {
			
			result.add(new HashSet<Set<String>>(group.keys));
		}
	}
}

package keyDecision;
import java.util.*;

import objetsconversations.*;
import parralelisation.*;
public class KeyFinder {
	/**
	* Find the keys which can be used as global keys
	* @param  allFilesGroup group of every keys from a file
	* @param  group group of keys found which are used together by the different mixed of files keys
	* @param  pool  thread pool
	* @param  position position of the file in the list of files
	* @param  result saving of the combination of all keys working together
	* @return      void
	*/
	public static synchronized void findingKeys(GroupOfAllFilesMinimized allFilesGroup, KeysFound group , ThreadExecutorFinding pool, int position, Set<HashSet<Set<String>>> result) {
		
			if(!(position+1==allFilesGroup.groupOfAllFiles.size())) {
				GroupOfKeysInOneFile allInOneFile=allFilesGroup.groupOfAllFiles.get(position);
				GroupOfKeysInOneFile newGroupWhenKeysInCommon=new GroupOfKeysInOneFile();
				
				for(KeysFound keysToWorkWith: allInOneFile.groupOfKeysFound) {
					System.out.println("les unauthorized");
					System.out.println(keysToWorkWith.unauthorizedKeys);
					System.out.println(group.unauthorizedKeys);
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


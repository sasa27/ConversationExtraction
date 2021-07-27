package parralelisation;

import java.util.Set;

import keyDecision.*;

public class TaskMinimizing implements Runnable{
	public GroupOfKeysInOneFile groupOfFoundKeys;
	public GroupOfAllFiles groupOfAllMinilized;
	public TaskMinimizing(GroupOfKeysInOneFile file, GroupOfAllFiles group) {
		this.groupOfFoundKeys=file;
		this.groupOfAllMinilized=group;
		
	}
	
    
    @Override
    public void run() {
         keyDecision.Minimize.minimize(this.groupOfFoundKeys, this.groupOfAllMinilized);
    }
}

package parralelisation;

import java.util.Set;

import keyDecision.GroupOfKeys;

public class TaskMinimizing implements Runnable{
	public GroupOfKeys groupOfFoundKeys;
	public TaskMinimizing(GroupOfKeys Group) {
		this.groupOfFoundKeys=Group;
	}
	
    
    @Override
    public void run() {
         keyDecision.Minimize.minimize(this.groupOfFoundKeys);
    }
}

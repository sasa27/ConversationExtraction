package parralelisation;

import java.util.Set;

import keyDecision.GroupOfKeys;

public class TaskMinimizing implements Runnable{
	public GroupOfKeys Group;
	public Set<Set<String>> ListKeyNow;
	public Set<Set<String>> listUnauthorizedKeysNow;
	public int position;
	public ThreadExecutorSimpleBlockingQueue pool;
	public TaskMinimizing(GroupOfKeys Group, ThreadExecutorSimpleBlockingQueue pool, int position) {
		this.Group=Group;
		this.position=position;
		this.pool=pool;
	}
	
    
    @Override
    public void run() {
         keyDecision.Minimize.minimize(this.Group, this.pool, this.position);
    }
}

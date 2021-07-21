package parralelisation;
import split.Trace;
import split.Regex;

import java.util.Set;

import keyDecision.GroupOfKeys;
import main.Save;
import metrique.Metric;
import objetsconversations.ConversationSet;
public class TaskFinder implements Runnable{
	public GroupOfKeys Group;
	public Set<Set<String>> ListKeyNow;
	public Set<Set<String>> listUnauthorizedKeysNow;
	public int position;
	public ThreadExecutorSimpleBlockingQueue pool;
	public TaskFinder(GroupOfKeys Group,Set<Set<String>> ListKeyNow,Set<Set<String>> listUnauthorizedKeysNow, ThreadExecutorSimpleBlockingQueue pool, int position) {
		this.Group=Group;
		this.ListKeyNow=ListKeyNow;
		this.listUnauthorizedKeysNow=listUnauthorizedKeysNow;
		this.position=position;
		this.pool=pool;
	}
	
    
    @Override
    public void run() {
         keyDecision.KeyFinder.findingKeys(this.Group, this.ListKeyNow, this.listUnauthorizedKeysNow,this.pool, this.position);
        
    }    
}
    

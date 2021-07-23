package parralelisation;
import split.Trace;

import split.Regex;

import java.util.Set;

import keyDecision.*;
import main.Save;
import metrique.Metric;
import objetsconversations.ConversationSet;
public class TaskFinder implements Runnable{
	public KeysFound groupNow;
	public int position;
	public ThreadExecutorSimpleBlockingQueue pool;
	public GroupOfAllFiles allFilesGroup;
	public TaskFinder(GroupOfAllFiles allFilesGroup,KeysFound groupNow, ThreadExecutorSimpleBlockingQueue pool, int position) {
		this.groupNow=groupNow;
		this.pool=pool;
		this.position=position;
		this.allFilesGroup=allFilesGroup;
	}
	
    
    @Override
    public void run() {
         keyDecision.KeyFinder.findingKeys(this.allFilesGroup,this.groupNow,this.pool, this.position);
        
    }    
}
    

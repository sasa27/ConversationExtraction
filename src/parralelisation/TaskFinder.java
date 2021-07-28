package parralelisation;
import split.Trace;

import split.Regex;

import java.util.HashSet;
import java.util.Set;

import keyDecision.*;
import main.Save;
import metrique.Metric;
import objetsconversations.ConversationSet;
public class TaskFinder implements Runnable{
	public KeysFound groupNow;
	public int position;
	public ThreadExecutorFinding pool;
	public GroupOfAllFilesMinimized allFilesGroup;
	public TaskFinder(GroupOfAllFilesMinimized allFilesGroup2,KeysFound groupNow, ThreadExecutorFinding pool2, int position) {
		this.groupNow=groupNow;
		this.pool=pool2;
		this.position=position;
		this.allFilesGroup=allFilesGroup2;
		
	}
	
    
    @Override
    public void run() {
         keyDecision.KeyFinder.findingKeys(this.allFilesGroup,this.groupNow,this.pool, this.position,this.pool.result);
        
    }    
}
    

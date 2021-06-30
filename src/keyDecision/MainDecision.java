package keyDecision;
import java.util.ArrayList;
import java.util.Set;

import parralelisation.*;
import objetsconversations.*;

public class MainDecision {
	public void main(){
		ThreadExecutorSimpleBlockingQueue pool = new ThreadExecutorSimpleBlockingQueue();
		GroupOfKeys CreatedGroup = new GroupOfKeys();
		for(KeysFound S : CreatedGroup.groupOfKeysFound){
			pool.SubmitMinimizingTask(MinimizingTask(S, CreatedGroup));
		}
		Wait end of Pool;
		newPool = new ThreadExecutorSimpleBlockingQueue();
		firstS=CreatedGroup.getS(0);
		for( Set<String> firstKeys: firstS.Keys) {
			Add FindingTask(S) to Pool;
		}
		Wait end of Pool;
	}
}

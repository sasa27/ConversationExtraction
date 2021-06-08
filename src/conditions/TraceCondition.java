package conditions;
import objetsconversations.*;

import metrique.*;

//implémenter un parser du premier ordre pour les conditions
//pour plus tard
public class TraceCondition {
    public static boolean verifyTrace(ConversationSet trace){
        return true;
    }
    public static boolean treshold(ConversationSet trace, int ligne){
    	if (ligne>4) {
    	if (Metric.GetMetriquePersonnel(trace)>=0.8) {
    		return true;
    	}
    	return false;
    	}
    	return true;
    }
    
}


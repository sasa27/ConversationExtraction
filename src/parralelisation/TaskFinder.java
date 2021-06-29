package parralelisation;
import split.Trace;
import split.Regex;
import main.Save;
import metrique.Metric;
import objetsconversations.ConversationSet;
public class TaskFinder implements Runnable{

   
    //le run pour le threadpool
    @Override
    public void run() {
         algo.FindConversation.find(this.getensembleconv(), this.getligne(), this.getbool(),this.gettrace(), this.getthreadpool(), this.regex, this.sauv);
        
    }    
}
    

package parralelisation;
import split.Trace;
import split.Regex;
import main.Save;
import metrique.MetriquePersonnel;
import objetsconversations.ConversationSet;
public class Task implements Runnable{
    /**
     * Une tache est créée grâce : au nom de la tache, son ensemble de conversation dans lequel on rajoutera le prochain event
     * la ligne du fichier afin de savoir a quel event nous étions
     * le boolean qui nous dit si nous sommes ou non a la fin du fichier
     * le fichier transformé en trace
     * le threadpool dans lequel celle-ci s'exécute
     */

    public String name;
    public ConversationSet ensembleconv;
    public int ligne;
    public boolean bool;
    public Trace trace;
    public ThreadExecutor threadpool;
    public float priority;
    public MetriquePersonnel metrique;
    public Save sauv;
    public Regex regex;
    public Task(String name, ConversationSet ensembleconv, int ligne , boolean bool, Trace trace, ThreadExecutor threadpool,  Regex regex,Save sauv){
        this.name = name; 
        this.ensembleconv=ensembleconv;
        this.ligne=ligne;
        this.bool=bool;
        this.trace=trace ;
        this.threadpool=threadpool;
        this.priority=getHeuristique();
        this.regex=regex;
        this.sauv=sauv;
    }
 
    public String getName() {
        return name;
    }
    
    
    
    public ConversationSet getensembleconv() {
        return ensembleconv;
    }
    public int getligne() {
        return ligne;
    }
    public boolean getbool() {
        return bool;
    }
    public Trace gettrace() {
        return trace;
    }
    public ThreadExecutor getthreadpool() {
        return threadpool;
    }

    //obtient l'heuristique de la tâche
    public float getHeuristique(){
        ConversationSet ensemble=this.getensembleconv();
        return MetriquePersonnel.GetMetriquePersonnel(ensemble);

    }
   
    //le run pour le threadpool
    @Override
    public void run() {
         algo.FindConversation.Find(this.getensembleconv(), this.getligne(), this.getbool(),this.gettrace(), this.getthreadpool(), this.regex, this.sauv);
        
    }
    
    }
    


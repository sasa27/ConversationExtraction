package parralelisation;

import java.util.concurrent.FutureTask;
import java.util.concurrent.Future;

public class MyFutureTaskFinding<T> extends FutureTask<T> {

    private TaskFinder task = null;

    public  MyFutureTaskFinding(TaskFinder task){
      super(task,null);
      this.task = task;
    }    
}
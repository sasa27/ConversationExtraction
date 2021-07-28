package parralelisation;

import java.util.concurrent.FutureTask;
import java.util.concurrent.Future;

public class MyFutureTaskMinimize<T> extends FutureTask<T> {

    private TaskMinimizing task = null;

    public  MyFutureTaskMinimize(TaskMinimizing task){
      super(task,null);
      this.task = task;
    }    
}
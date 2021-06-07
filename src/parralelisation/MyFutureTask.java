package parralelisation;

import java.util.concurrent.FutureTask;
import java.util.concurrent.Future;

public class MyFutureTask<T> extends FutureTask<T> implements Comparable<MyFutureTask<T>> {

    private Task task = null;

    public  MyFutureTask(Task task){
      super(task,null);
      this.task = task;
    }
    @Override
    public int compareTo(MyFutureTask<T> that) {
        return Float.compare(-this.task.priority, -that.task.priority);
    }
    
}
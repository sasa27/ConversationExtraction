package parralelisation;

import java.util.concurrent.*;
class CustomThreadPoolExecutor extends ThreadPoolExecutor{
    public CustomThreadPoolExecutor(PriorityBlockingQueue<Runnable> queue) {
        super(Runtime.getRuntime().availableProcessors(),Runtime.getRuntime().availableProcessors() , 100000, TimeUnit.SECONDS, queue);
        
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
        return new MyFutureTask<>((Task)runnable);
    }
}
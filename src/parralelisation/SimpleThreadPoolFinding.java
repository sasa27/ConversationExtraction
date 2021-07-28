package parralelisation;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SimpleThreadPoolFinding extends ThreadPoolExecutor{
    public SimpleThreadPoolFinding(BlockingQueue<Runnable> queue) {
		super(Runtime.getRuntime().availableProcessors(),Runtime.getRuntime().availableProcessors() , 100000, TimeUnit.SECONDS,queue);
	}

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
        return new MyFutureTaskFinding<>((TaskFinder)runnable);
    }

}
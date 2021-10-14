package parralelisation;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SimpleThreadPool extends ThreadPoolExecutor{
    public SimpleThreadPool(BlockingQueue<Runnable> queue) {
		super(Runtime.getRuntime().availableProcessors(),Runtime.getRuntime().availableProcessors() , 100000, TimeUnit.SECONDS,queue);
	}

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
        return new MyFutureTaskMinimize<>((TaskMinimizing)runnable);
    }

}

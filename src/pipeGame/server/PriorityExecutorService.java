package pipeGame.server;

import java.util.concurrent.*;

public class PriorityExecutorService<T extends Comparable & Runnable> {

    private ExecutorService executor;
    private BlockingQueue<T> queue;
    private boolean stop;

    public PriorityExecutorService(ExecutorService executorService, BlockingQueue<T> blockingQueue) {
        this.executor = executorService;
        this.queue = blockingQueue;
        this.stop = false;
        this.activeExecutor();
    }

    public void activeExecutor() {
        new Thread(()-> {
            while(!stop) {
                T toExecute = queue.poll();
                if(toExecute != null)
                    executor.execute(toExecute);
            }
        }).start();

    }

    void shutdownExecutor() {
        stop = true;
        executor.shutdown();
    }

    void add(T obj) {
        queue.offer(obj);
    }

}
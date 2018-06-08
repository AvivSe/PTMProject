package pipeGame.server;

import java.util.concurrent.*;

public class PriorityExecutorService<T extends Comparable & Runnable> {

    private ExecutorService executor;
    private BlockingQueue<T> queue;
    private boolean stop;
    private int pollingTimeOut;

    public PriorityExecutorService(ExecutorService executorService, BlockingQueue<T> blockingQueue, int pollingTimeOut) {
        this.pollingTimeOut = pollingTimeOut;
        this.executor = executorService;
        this.queue = blockingQueue;
        this.stop = false;
        this.activeExecutor();
    }

    public void activeExecutor() {
        new Thread(()-> {
            while(!stop) {
                try {
                    T toExecute = queue.poll(pollingTimeOut, TimeUnit.MILLISECONDS);

                    if(toExecute != null)
                        executor.execute(toExecute);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
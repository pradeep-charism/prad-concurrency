package pools;

import java.util.concurrent.*;

public class RunnableAndExecutorServicePattern {

    public static void main(String[] args) {
        Runnable task = () -> System.out.println("Hellow its me..."+ Thread.currentThread().getName());

        Callable<String> callable = () -> "Calling from callable: "+ Thread.currentThread().getName();

        usingThreads(task);
        usingExecutorServiceSingleThreadExecutor(task);
        usingFixedThreadPoolExecutor(callable);
    }

    private static void usingFixedThreadPoolExecutor(Callable<String> callable) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            Future<String> futures = fixedThreadPool.submit(callable);
            try {
                System.out.println(futures.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        fixedThreadPool.shutdown();
    }

    private static void usingExecutorServiceSingleThreadExecutor(Runnable task) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            executorService.execute(task);
        }
        executorService.shutdown();
    }

    private static void usingThreads(Runnable task) {
        for (int i = 0; i < 10; i++) {
            new Thread(task).start();
        }
    }


}

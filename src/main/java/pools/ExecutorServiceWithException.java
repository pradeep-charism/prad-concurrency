package pools;

import java.util.concurrent.*;

public class ExecutorServiceWithException {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        usingFixedThreadPoolExecutorThrowsExceptionCausedRuntimeException();
        usingFixedThreadPoolExecutorThrowsExceptionCausedByTimeout();
    }

    private static void usingFixedThreadPoolExecutorThrowsExceptionCausedByTimeout() throws ExecutionException, InterruptedException, TimeoutException {
        Callable<String> callable = () -> {
            Thread.sleep(300);
            return "Calling from callable: " + Thread.currentThread().getName();
        };

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
        try {
            for (int i = 0; i < 10; i++) {
                Future<String> futures = fixedThreadPool.submit(callable);
                System.out.println(futures.get(100, TimeUnit.MILLISECONDS));
            }
        } finally {
            fixedThreadPool.shutdown();
        }
    }

    private static void usingFixedThreadPoolExecutorThrowsExceptionCausedRuntimeException() throws InterruptedException, ExecutionException, TimeoutException {
        Callable<String> callable = () -> {
            throw new IllegalStateException("I am throwing a exception : " + Thread.currentThread().getName());
        };
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
        try {
            for (int i = 0; i < 10; i++) {
                Future<String> futures = fixedThreadPool.submit(callable);
                System.out.println(futures.get(100, TimeUnit.MILLISECONDS));
            }
        } finally {
            fixedThreadPool.shutdown();

        }
    }


}

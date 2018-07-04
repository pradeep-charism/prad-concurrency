package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentyProducerConsumerByLocks {

    Lock lock = new ReentrantLock();
    Condition isEmpty = lock.newCondition();
    Condition isFull = lock.newCondition();

    List<Integer> buffer = new ArrayList<>();

    class Consumer implements Callable<String> {

        @Override
        public String call() throws Exception {
            int count = 0;
            while (count++ < 50) {
                try {
                    lock.lock();
                    while (isEmpty(buffer)) {
                        // Wait
                        if(!isEmpty.await(10, TimeUnit.MILLISECONDS)){
                         throw new TimeoutException("Consumer timeout..");
                        }
                    }
                    buffer.remove(buffer.size() - 1);
                    // Signal
                    isFull.signalAll();
                } finally {
                    lock.unlock();
                }
            }

            return "Consumed: " + (count - 1);
        }
    }

    class Producer implements Callable<String> {

        @Override
        public String call() throws Exception {
            int count = 0;
            while (count++ < 50) {
                try {
                    lock.lock();
                    while (isFull(buffer)) {
                        //Wait
                        isFull.await();
                    }
                    buffer.add(1);
                    //Signal
                    isEmpty.signalAll();
                } finally {
                    lock.unlock();
                }
            }
            return "Produced: " + (count - 1);
        }
    }

    private boolean isEmpty(List<Integer> buffer) {
        return buffer.isEmpty();
    }

    private boolean isFull(List<Integer> buffer) {
        return buffer.size() == 50;
    }

    public void execute() throws InterruptedException {
        List<Producer> producers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            producers.add(new Producer());
        }

        List<Consumer> consumers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            consumers.add(new Consumer());
        }

        List<Callable<String>> producersAndConsumers = new ArrayList<>();
        producersAndConsumers.addAll(producers);
        producersAndConsumers.addAll(consumers);

        ExecutorService executorService = Executors.newFixedThreadPool(8);
        try {
            System.out.println("Producers & Consumers launched.");
            List<Future<String>> futures = executorService.invokeAll(producersAndConsumers);

            futures.forEach(future -> {
                try {
                    String result = future.get();
                    System.out.println(result);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                } catch (ExecutionException e1) {
                    e1.printStackTrace();
                }
            });
        } finally {
            System.out.println("Shutting down the service...");
            executorService.shutdown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConcurrentyProducerConsumerByLocks example = new ConcurrentyProducerConsumerByLocks();
        example.execute();
    }

}

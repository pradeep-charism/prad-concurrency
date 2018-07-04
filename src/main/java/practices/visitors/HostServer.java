package practices.visitors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HostServer {

    static final int NUM_OF_THREADS = 10;

    public static void main(String[] args) {
        List<Callable<String>> callables = new ArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_THREADS);
        for (int i = 0; i < NUM_OF_THREADS; i++) {
            callables.add(new Visitor(new Website(), "Singapore"));
            callables.add(new Visitor(new Website(), "Australia"));
            callables.add(new Visitor(new Website(), "UK"));
            callables.add(new Visitor(new Website(), "Ireland"));
        }

        try {
            List<Future<String>> futures = executorService.invokeAll(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

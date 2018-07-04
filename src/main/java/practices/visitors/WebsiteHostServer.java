package practices.visitors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WebsiteHostServer {

    static final int NUM_OF_THREADS = 100;
    static final boolean SAFE_SITE = true;

    public static void main(String[] args) {
        List<Callable<String>> callables = new ArrayList<>();
        Website website = getWebsite(SAFE_SITE);

        ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_THREADS);
        for (int i = 0; i < NUM_OF_THREADS; i++) {
            callables.add(new Visitor(website, "Singapore"));
            callables.add(new Visitor(website, "Australia"));
            callables.add(new Visitor(website, "UK"));
            callables.add(new Visitor(website, "Sweden"));
            callables.add(new Hacker(website, "India"));
            callables.add(new Visitor(website, "US"));
            callables.add(new Visitor(website, "Canada"));
            callables.add(new Hacker(website, "China"));
            callables.add(new Visitor(website, "Ireland"));
            callables.add(new Hacker(website, "Russia"));
        }

        try {
            List<Future<String>> futures = executorService.invokeAll(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Website getWebsite(boolean isSafeSite) {
        return isSafeSite ? new SafeWebsite() : new UnsafeWebsite();
    }
}

package practices.visitors;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class SafeWebsiteAtomic implements Website{

    public AtomicInteger visitors = new AtomicInteger();

    public static Set<Integer> allVisitors = new HashSet<>();

    @Override
    public int registerVisitors(String country) {
        int id = visitors.incrementAndGet();
        try {
            if (allVisitors.contains(id)) {
                System.out.println("Duplicate visitor registration..."+ id+ " from: "+ country);
                System.exit(1);
            } else {
//                Thread.sleep(1);
                allVisitors.add(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
}

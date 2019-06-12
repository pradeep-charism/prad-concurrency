package practices.visitors;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class SafeWebsite implements Website{

    public int visitors = 0;

    public static Map<String, String> allVisitors = new HashMap<>();

    @Override
    public int registerVisitors(String country) {
        int id = getId();
        try {
//            get(country, id);
////                Thread.sleep(1);
//            put(country, id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void get(String country, int id) {
        if (allVisitors.containsKey(country)) {
            String status = allVisitors.get(country);
            System.out.println("Visitor already logged and browsing..."+ id+ " from: "+ country + " with status: "+ status);
//                allVisitors.put(country, "Browsing...");
//                System.exit(1);
        }
    }

    @Override
    public void put(String country, int id) {
        System.out.println("New Visitor logged in..."+ id+ " from: "+ country);
        allVisitors.put(country, "Browsing...");
    }

    private synchronized int getId() {
        return visitors++;
    }
}

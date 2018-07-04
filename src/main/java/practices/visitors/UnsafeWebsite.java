package practices.visitors;

import java.util.HashSet;
import java.util.Set;

public class UnsafeWebsite implements Website{

    public static int visitors = 0;
//    public  int visitors = 0;

//    public Set<Integer> allVisitors = new HashSet<>();
    public static Set<Integer> allVisitors = new HashSet<>();

    @Override
    public int registerVisitors(String country) {
        int id = visitors++;
        if (allVisitors.contains(id)) {
            System.out.println("Duplicate visitor registration..."+ id+ " from: "+ country);
            System.exit(1);
        } else {
            allVisitors.add(id);
        }
        return id;
    }
}

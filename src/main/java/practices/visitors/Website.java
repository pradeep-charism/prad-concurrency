package practices.visitors;

import java.util.HashSet;
import java.util.Set;

public class Website {

    public int visitors = 0;
    public static Set<Integer> allVisitors = new HashSet<>();

    public int registerVisitors() {
        int id = visitors++;
        if (allVisitors.contains(id)) {
            System.out.println("Duplicate visitor registration...");
            System.exit(1);
        } else {
            allVisitors.add(id);
        }
        return id;
    }
}

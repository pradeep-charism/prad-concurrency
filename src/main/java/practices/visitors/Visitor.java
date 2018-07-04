package practices.visitors;

import java.util.concurrent.Callable;

public class Visitor implements Callable<String> {

    private Website website;
    private String country;

    public Visitor(Website website, String country) {
        this.website = website;
        this.country = country;
    }

    @Override
    public String call() throws Exception {
        while (true){
            int id = website.registerVisitors();
            System.out.println("Visitor: "+ id + "Country: "+ country);
        }
    }
}

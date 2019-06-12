package practices.visitors;

import java.util.concurrent.Callable;

public class Hacker implements Callable<String> {

    private Website website;
    private String country;

    public Hacker(Website website, String country) {
        this.website = website;
        this.country = country;
    }

    @Override
    public String call() throws Exception {
        while (true){
            int id = website.registerVisitors(country);
//            System.out.println("Hacker: "+ id + " Country: "+ country);
            website.get(country, id);
        }
    }
}

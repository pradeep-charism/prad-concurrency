package threads;

import java.util.Arrays;

public class GreetingSingleThread {

    public static void main(String[] args) {
        String[] countries = {"Singapore", "France", "Australia", "New York"};
        Arrays.asList(countries).stream().forEach(e-> {
            long id = Thread.currentThread().getId();
            System.out.println(id + ": Hello "+ e +"!");
        });
    }
}

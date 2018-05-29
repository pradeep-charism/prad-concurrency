package threads;

import java.util.Arrays;

public class GreetingCountries {

    public static class GreetingThread extends Thread {

        private String country;

        public GreetingThread(String country) {
            this.country = country;
        }

        @Override
        public void run() {
            long id = Thread.currentThread().getId();
            System.out.println(id + ": Hello " + country + "!");
        }
    }

    public static void main(String[] args) {
        String[] countries = {"Singapore", "France", "Australia", "New York"};
        Arrays.asList(countries).stream().forEach(e -> new GreetingThread(e).start());
    }

}

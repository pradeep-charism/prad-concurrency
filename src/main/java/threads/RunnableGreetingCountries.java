package threads;

import java.util.Arrays;

public class RunnableGreetingCountries {

    public static class GreetingRunnable implements Runnable {

        private String country;

        public GreetingRunnable(String country) {
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
        Arrays.asList(countries).stream().forEach(e -> new Thread(new GreetingRunnable(e)).start());
    }

}

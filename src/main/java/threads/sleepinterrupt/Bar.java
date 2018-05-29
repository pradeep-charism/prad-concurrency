package threads.sleepinterrupt;

import java.util.concurrent.TimeUnit;

public class Bar {

    public static void main(String[] args) {

        Bartender bartender = new Bartender();
        Thread bartenderThread = new Thread(bartender, "Bartender");

        bartenderThread.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("Bar is interrupted...");
        }

        int numberOfCustomer= 5;
        for (int i=1; i<= numberOfCustomer; i++){
            String customerName = "Customer="+i;
            Customer customer = new Customer(bartenderThread, customerName, (int) Math.random()*10);
            new Thread(customer, customerName).start();
        }
    }

}

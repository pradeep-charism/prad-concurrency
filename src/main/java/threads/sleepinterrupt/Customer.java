package threads.sleepinterrupt;

import java.util.concurrent.TimeUnit;

public class Customer implements Runnable {

    private Thread bartenderThread;
    private String name;
    long waitTime;

    public Customer(Thread bartenderThread, String name, int waitTime) {
        this.bartenderThread = bartenderThread;
        this.name = name;
        this.waitTime = waitTime;
    }

    @Override
    public void run() {
        System.out.println(name + ": Doesn't anyone seem to be here? I'll wait a moment.");

        try {
            TimeUnit.SECONDS.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(name+ ": Oh there's a bell! Can i get some service around here.");

        bartenderThread.interrupt();
    }
}

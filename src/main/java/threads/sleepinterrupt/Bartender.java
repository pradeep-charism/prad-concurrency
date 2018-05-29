package threads.sleepinterrupt;

import java.util.concurrent.TimeUnit;

public class Bartender implements Runnable{


    @Override
    public void run() {
        System.out.println("Bartender. My boss isnt in today, time for quick snooze! Haha...");

        while (true){
            if(Thread.interrupted()){
                System.out.println("Bartender. Zzz err erm. Is someone waiting?");
            }

            try {
                System.out.println("I am going to sleep for a while...");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                System.out.println("Bartender is interrupted...");
                Thread.currentThread().interrupt();
            }


        }
    }
}

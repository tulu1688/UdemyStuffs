package com.java.thread.lecture14_interuptingthread;

import java.util.Random;

/**
 * Created by trankhai on 7/12/17.
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting...");
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                Random random = new Random();
                for (int i=0;i<1E6; i++) {
//                    if (Thread.currentThread().isInterrupted()) {
//                        System.out.println("Interrupted");
//                        break;
//                    }

                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }

                    Math.sin(random.nextDouble());
                }
            }
        });
        t1.start();

        Thread.sleep(500);
        t1.interrupt();

        t1.join();
        System.out.println("Finish.");
    }
}

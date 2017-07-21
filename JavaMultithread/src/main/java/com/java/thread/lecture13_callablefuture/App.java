package com.java.thread.lecture13_callablefuture;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by trankhai on 7/12/17.
 */
public class App {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Future<Integer> future =  executor.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                Random random = new Random();
                int duration = random.nextInt(4000);

                if (duration > 2000) {
                    throw new Exception("Sleeping for too long");
                }

                System.out.println("Starting...");
                Thread.sleep(duration);
                System.out.println("Finish.");

                return duration;
            }
        });

        executor.shutdown();

        try {
            System.out.println("Result is: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

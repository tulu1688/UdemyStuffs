package com.java.thread.lecture12_semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by trankhai on 7/12/17.
 */
public class App {
    public static void main(String[] args) throws Exception{
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i=0;i<2000;i++) {
            executor.submit(new Runnable() {
                public void run() {
                    Connection.getInstance().connect();
                }
            });
        }

        executor.shutdown();

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}

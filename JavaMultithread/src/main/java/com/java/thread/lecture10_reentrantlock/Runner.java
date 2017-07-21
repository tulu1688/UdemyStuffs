package com.java.thread.lecture10_reentrantlock;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by trankhai on 7/12/17.
 */
public class Runner {
    private int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    private void increment() {
        for (int i=0;i<10000;i++){
            count++;
        }
    }

    public void firstThread() throws InterruptedException {
        lock.lock();

        System.out.println("Waiting...");
        cond.await();
        System.out.println("Woken up!");

        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void secondThread() throws InterruptedException {
        Thread.sleep(1000);
        lock.lock();

        System.out.println("Press return key...");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Got return key!");

        cond.signal();

        try {
            increment();
        } finally {
            lock.unlock();
        }

    }

    public void finished() {
        System.out.println("Count is: " + count);
    }
}

package com.java.thread.lecture9_lowlevelsynchronization;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by trankhai on 7/12/17.
 */
public class Processor {
    private LinkedList<Integer> list = new LinkedList<Integer>();
    private final int LIMIT = 10;
    private Object lock = new Object();

    public void procedure() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (lock) {
                while (list.size() == LIMIT) {
                    lock.wait();
                }

                list.add(value++);
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException {
        Random random = new Random();

        while (true){
            synchronized (lock) {
                while (list.size() == 0) {
                    lock.wait();
                }

                System.out.print("List size: " + list.size());
                int value = list.removeFirst();
                System.out.println("; value is: " + value);
                lock.notify();
            }

            Thread.sleep(random.nextInt(1000));
        }
    }
}

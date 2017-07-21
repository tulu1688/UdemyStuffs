package com.java.thread.lecture8_waitAndNotify;

import java.util.Scanner;

/**
 * Created by trankhai on 7/12/17.
 */
public class Processor {
    public void procedure() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer thread runing ...");
            wait();
            System.out.println("Resume.");
        }
    }

    public void consume() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);

        synchronized (this) {
            System.out.println("Waiting for return key.");
            scanner.nextLine();
            System.out.println("Return key pressed");
            notify();
        }
    }
}
